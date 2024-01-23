package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5ClientBuilder
import org.alexcawl.iot_connector.common.model.Login
import org.alexcawl.iot_connector.common.model.Password
import java.util.UUID
import javax.inject.Inject

class MqttAsyncClientFactory @Inject constructor() : IMqttClientFactory<Mqtt5AsyncClient> {
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