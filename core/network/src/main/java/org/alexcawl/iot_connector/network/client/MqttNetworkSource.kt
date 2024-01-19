package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.alexcawl.iot_connector.common.exceptions.ProfileNotSelectedException
import org.alexcawl.iot_connector.network.subscribe
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import javax.inject.Inject

class MqttNetworkSource @Inject constructor(
    private val profileDatabaseDao: ProfileDatabaseDao,
    private val profileDatastoreDao: ProfileDatastoreDao
) : IMqttNetworkSource {
    private val asyncClient: Flow<Result<Mqtt5AsyncClient>> = flow {
        profileDatastoreDao.getSelectedProfileId().collect { uuid ->
            emit(
                runCatching {
                    val selectedId = uuid ?: throw ProfileNotSelectedException()
                    val selectedProfile = profileDatabaseDao.getProfile(selectedId) ?: throw ProfileNotSelectedException()
                    val client = with(selectedProfile) {
                        buildAsyncClient(
                            id = id,
                            host = configuration.host,
                            port = configuration.port,
                            login = configuration.login,
                            password = configuration.password
                        )
                    }
                    client.connect()
                    client
                }
            )
        }
    }

    override val client: Flow<Result<MqttClient>> = asyncClient.map { it }

    override fun subscribe(endpoint: String): Flow<Result<Mqtt5Publish>> = channelFlow {
        asyncClient.collect { possibleClient ->
            try {
                val client: Mqtt5AsyncClient = possibleClient.getOrThrow()
                client.subscribe(endpoint) { publish ->
                    runBlocking {
                        send(Result.success(publish))
                    }
                }
            } catch (exception: RuntimeException) {
                send(Result.failure(exception))
            }
        }
    }
}

