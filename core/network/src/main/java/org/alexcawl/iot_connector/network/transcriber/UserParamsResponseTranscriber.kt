package org.alexcawl.iot_connector.network.transcriber

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import org.alexcawl.iot_connector.network.dto.UserParamsResponse
import javax.inject.Inject

class UserParamsResponseTranscriber @Inject constructor() : IMqttResponseTranscriber<Mqtt5Publish> {
    override fun transcribe(message: Mqtt5Publish): Result<MqttResponsePayload> = runCatching {
        UserParamsResponse(
            message.userProperties.asList().associate {
                Pair(
                    it.name.toString(),
                    it.value.toString()
                )
            }
        )
    }
}