package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.alexcawl.iot_connector.common.exceptions.ProfileNotFoundException
import org.alexcawl.iot_connector.common.exceptions.ProfileNotSelectedException
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import javax.inject.Inject

class Mqtt5AsyncClientSource @Inject constructor(
    private val profileDatabaseDao: ProfileDatabaseDao,
    private val profileDatastoreDao: ProfileDatastoreDao
) : IMqttClientSource<Mqtt5AsyncClient> {
    private val asyncClient: Flow<Result<Mqtt5AsyncClient>> = flow {
        profileDatastoreDao.getSelectedProfileId().collect { uuid ->
            emit(
                runCatching {
                    val selectedId = uuid ?: throw ProfileNotSelectedException()
                    val selectedProfile = profileDatabaseDao.getProfile(selectedId) ?: throw ProfileNotFoundException()
                    with(selectedProfile) {
                        buildAsyncClient(
                            id = id,
                            host = configuration.host,
                            port = configuration.port,
                            login = configuration.login,
                            password = configuration.password
                        )
                    }
                }
            )
        }
    }

    override val connectedClient: Flow<Result<Mqtt5AsyncClient>> = channelFlow {
        asyncClient.collect {
            try {
                val client: Mqtt5AsyncClient = it.getOrThrow()
                client.connect().whenComplete { connection: Mqtt5ConnAck?, exception: Throwable? ->
                    runBlocking {
                        send(
                            when {
                                connection != null -> Result.success(client)
                                exception != null -> Result.success(client)
                                else -> Result.failure(IllegalStateException())
                            }
                        )
                    }
                }
            } catch (exception: RuntimeException) {
                runBlocking {
                    send(Result.failure(exception))
                }
            }
        }
    }
}