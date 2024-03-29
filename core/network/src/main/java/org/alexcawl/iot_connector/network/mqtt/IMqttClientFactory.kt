package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.MqttClient
import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.Login
import org.alexcawl.iot_connector.common.model.Password
import java.util.UUID

interface IMqttClientFactory<T : MqttClient> {
    val client: Flow<Result<T>>

    fun create(identifier: UUID, host: String, port: Int): T

    fun create(identifier: UUID, host: String, port: Int, login: Login): T

    fun create(identifier: UUID, host: String, port: Int, password: Password): T

    fun create(identifier: UUID, host: String, port: Int, login: Login, password: Password): T
}