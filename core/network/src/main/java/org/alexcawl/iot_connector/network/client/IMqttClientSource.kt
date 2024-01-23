package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.MqttClient
import kotlinx.coroutines.flow.Flow

interface IMqttClientSource <C : MqttClient> {
    val connectedClient: Flow<Result<C>>
}