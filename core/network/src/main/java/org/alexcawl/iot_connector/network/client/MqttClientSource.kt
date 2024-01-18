package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import org.alexcawl.iot_connector.persistence.entity.ProfileEntity
import java.util.UUID
import javax.inject.Inject

class MqttClientSource @Inject constructor(
    private val profileDatabaseDao: ProfileDatabaseDao,
    profileDatastoreDao: ProfileDatastoreDao
) : IMqttClientSource {
    override val client: Flow<MqttClient> = profileDatastoreDao.getSelectedProfileId().map { selectedId ->
        when (selectedId) {
            null -> MqttClient.Empty
            else -> MqttClient.Client(buildDebugClient())
//            when (val selectedProfile = profileDatabaseDao.getProfile(selectedId)) {
//                null -> MqttClient.Empty
//                else -> MqttClient.Client(buildClient(selectedProfile))
//            }
        }
    }

    private fun buildClient(profile: ProfileEntity): Mqtt5BlockingClient =
        Mqtt5Client.builder()
            .identifier(profile.id.toString())
            .serverHost(profile.configuration.host)
            .serverPort(profile.configuration.port)
            .run {
                val username: String? = profile.configuration.login
                val password: String? = profile.configuration.password
                when {
                    username != null && password != null -> this.simpleAuth()
                        .username(username)
                        .password(password.toByteArray())
                        .applySimpleAuth()
                    username != null -> this.simpleAuth()
                        .username(username)
                        .applySimpleAuth()
                    password != null -> this.simpleAuth()
                        .password(password.toByteArray())
                        .applySimpleAuth()
                    else -> this
                }
            }
            .build()
            .toBlocking()

    private companion object {
        fun buildDebugClient(): Mqtt5BlockingClient =
            Mqtt5Client.builder()
            .identifier(UUID.randomUUID().toString())
            .serverHost("192.168.31.102")
            .serverPort(1883)
            .build()
            .toBlocking()
    }
}