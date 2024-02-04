package org.alexcawl.iot_connector.client.di

import androidx.lifecycle.ViewModelProvider
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import org.alexcawl.iot_connector.network.mqtt.IMqttClientHolder

interface ClientDependencies {
    val holder: IMqttClientHolder<Mqtt5AsyncClient>
    val viewModelFactory: ViewModelProvider.Factory
}