package org.alexcawl.iot_connector.network.transcriber

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import org.alexcawl.iot_connector.network.dto.TextResponse
import javax.inject.Inject

class TextResponseTranscriber @Inject constructor() : IMqttResponseTranscriber<Mqtt5Publish> {
    override fun transcribe(message: Mqtt5Publish): Result<MqttResponsePayload> = runCatching {
        with(message.payloadAsBytes) {
            TextResponse(this, this.decodeToString())
        }
    }
}