package org.alexcawl.iot_connector.network.client

import kotlinx.coroutines.flow.Flow

interface IMqttClientSource {
    val client: Flow<MqttClient>
}