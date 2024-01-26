package org.alexcawl.iot_connector.network.transcriber

import org.alexcawl.iot_connector.common.model.MqttResponsePayload

interface IMqttResponseTranscriber <M> {
    fun transcribe(message: M) : Result<MqttResponsePayload>
}