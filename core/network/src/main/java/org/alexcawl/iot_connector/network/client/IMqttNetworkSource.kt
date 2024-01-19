package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow

interface IMqttNetworkSource {
    val client: Flow<Result<MqttClient>>

    fun subscribe(endpoint: String): Flow<Result<Mqtt5Publish>>
}