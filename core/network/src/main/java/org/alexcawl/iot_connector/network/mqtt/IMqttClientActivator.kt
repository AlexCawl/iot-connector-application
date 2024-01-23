package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.MqttClient
import kotlinx.coroutines.flow.StateFlow
import org.alexcawl.iot_connector.common.util.Product

interface IMqttClientActivator <T : MqttClient> {
    val activatedClient: StateFlow<Product<T>>
}