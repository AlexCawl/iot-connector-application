package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5ClientBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.exceptions.ProfileNotSelectedException
import org.alexcawl.iot_connector.common.model.Login
import org.alexcawl.iot_connector.common.model.Password
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import java.util.UUID
import javax.inject.Inject

class MqttAsyncClientFactory @Inject constructor(
    private val profileDatabaseDao: ProfileDatabaseDao,
    profileDatastoreDao: ProfileDatastoreDao
) : IMqttClientFactory<Mqtt5AsyncClient> {
    override val client: Flow<Result<Mqtt5AsyncClient>> = profileDatastoreDao.getSelectedProfileId()
        .map { it?.let { profileDatabaseDao.getProfile(it) } }.map {
            runCatching {
                when (it) {
                    null -> throw ProfileNotSelectedException()
                    else -> with(it) {
                        with(it.configuration) {
                            when {
                                login != null && password != null -> create(
                                    identifier = id,
                                    host = host,
                                    port = port,
                                    login = Login(login!!),
                                    password = Password(password!!)
                                )

                                configuration.login != null -> create(
                                    identifier = id,
                                    host = host,
                                    port = port,
                                    login = Login(login!!)
                                )

                                password != null -> create(
                                    identifier = id,
                                    host = host,
                                    port = port,
                                    password = Password(password!!)
                                )

                                else -> create(
                                    identifier = id,
                                    host = host,
                                    port = port
                                )
                            }
                        }
                    }
                }
            }
        }

    override fun create(identifier: UUID, host: String, port: Int): Mqtt5AsyncClient =
        createBaseClientBuilder(identifier, host, port).build().toAsync()

    override fun create(
        identifier: UUID, host: String, port: Int, login: Login
    ): Mqtt5AsyncClient =
        createBaseClientBuilder(identifier, host, port).simpleAuth().username(login.content)
            .applySimpleAuth().build().toAsync()

    override fun create(
        identifier: UUID, host: String, port: Int, password: Password
    ): Mqtt5AsyncClient = createBaseClientBuilder(identifier, host, port).simpleAuth()
        .password(password.content.toByteArray()).applySimpleAuth().build().toAsync()

    override fun create(
        identifier: UUID, host: String, port: Int, login: Login, password: Password
    ): Mqtt5AsyncClient =
        createBaseClientBuilder(identifier, host, port).simpleAuth().username(login.toString())
            .password(password.content.toByteArray()).applySimpleAuth().build().toAsync()

    private fun createBaseClientBuilder(
        identifier: UUID, host: String, port: Int
    ): Mqtt5ClientBuilder =
        MqttClient.builder().identifier(identifier.toString()).serverHost(host).serverPort(port)
            .useMqttVersion5()
}