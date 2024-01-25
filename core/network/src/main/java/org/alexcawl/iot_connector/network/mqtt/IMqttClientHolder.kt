package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.MqttClient
import kotlinx.coroutines.flow.StateFlow

interface IMqttClientHolder <T : MqttClient> {
    val client: StateFlow<Result<T>>
}