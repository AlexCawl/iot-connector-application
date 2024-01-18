package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient

sealed interface MqttClient {
    data object Empty : MqttClient

    data class Client(val client: Mqtt5BlockingClient) : MqttClient
}