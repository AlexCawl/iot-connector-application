package org.alexcawl.iot_connector.network

import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import kotlinx.coroutines.flow.Flow

interface IMqttClientService {
    val client: Flow<Mqtt5Client>
}