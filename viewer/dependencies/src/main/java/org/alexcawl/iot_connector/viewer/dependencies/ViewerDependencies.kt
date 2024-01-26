package org.alexcawl.iot_connector.viewer.dependencies

import androidx.lifecycle.ViewModelProvider
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import org.alexcawl.iot_connector.network.mqtt.IMqttClientDao
import org.alexcawl.iot_connector.network.transcriber.IMqttResponseTranscriber

interface ViewerDependencies {
    val viewModelFactory: ViewModelProvider.Factory
    val clientDao: IMqttClientDao<Mqtt5Publish>
    val transcribers: Set<IMqttResponseTranscriber<Mqtt5Publish>>
}