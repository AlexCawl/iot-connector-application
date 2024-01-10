package org.alexcawl.iot_connector.network

import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import com.hivemq.client.mqtt.mqtt5.Mqtt5ClientBuilder

sealed interface MqttLazyClient {
    data object Empty : MqttLazyClient

    data class Building(val builder: Mqtt5ClientBuilder) : MqttLazyClient

    data class Client(val client: Mqtt5Client) : MqttLazyClient
}