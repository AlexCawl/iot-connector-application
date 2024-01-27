package org.alexcawl.iot_connector.network.transcriber

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import javax.inject.Inject

class DefaultResponseTranscriber @Inject constructor() : IMqttResponseTranscriber<Mqtt5Publish> {
    override fun transcribe(message: Mqtt5Publish): Result<MqttResponsePayload> = runCatching {
        object : MqttResponsePayload {
            override val raw: ByteArray = message.payloadAsBytes
        }
    }
}