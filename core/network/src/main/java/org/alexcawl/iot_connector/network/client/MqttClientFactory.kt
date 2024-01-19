package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.Mqtt5ClientBuilder
import org.alexcawl.iot_connector.common.model.Login
import org.alexcawl.iot_connector.common.model.Password
import java.util.UUID

fun buildAsyncClient(
    id: UUID, host: String, port: Int, login: String?, password: String?
): Mqtt5AsyncClient = when {
    login != null && password != null -> buildMqttClient(
        id,
        host,
        port,
        Login(login),
        Password(password)
    )
    login != null -> buildMqttClient(id, host, port, Login(login))
    password != null -> buildMqttClient(id, host, port, Password(password))
    else -> buildMqttClient(id, host, port)
}.toAsync()

internal fun buildMqttClient(
    id: UUID, host: String, port: Int
): Mqtt5Client = buildStandardMqttClient(id, host, port).build()

internal fun buildMqttClient(
    id: UUID, host: String, port: Int, login: Login
): Mqtt5Client = buildStandardMqttClient(id, host, port)
    .simpleAuth().username(login.content).applySimpleAuth()
    .build()

internal fun buildMqttClient(
    id: UUID, host: String, port: Int, password: Password
): Mqtt5Client = buildStandardMqttClient(id, host, port)
    .simpleAuth().password(password.content.toByteArray()).applySimpleAuth()
    .build()

internal fun buildMqttClient(
    id: UUID, host: String, port: Int, login: Login, password: Password
): Mqtt5Client = buildStandardMqttClient(id, host, port)
    .simpleAuth().username(login.toString()).password(password.content.toByteArray()).applySimpleAuth()
    .build()


private fun buildStandardMqttClient(
    id: UUID, host: String, port: Int
): Mqtt5ClientBuilder = MqttClient.builder()
    .identifier(id.toString())
    .serverHost(host)
    .serverPort(port)
    .useMqttVersion5()