package org.alexcawl.iot_connector.network.transcriber

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.serialization.json.Json
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import org.alexcawl.iot_connector.network.dto.SmallMatrixThermalData
import org.alexcawl.iot_connector.network.dto.SmallMatrixThermalResponse
import javax.inject.Inject

class SmallMatrixThermalResponseTranscriber @Inject constructor() : IMqttResponseTranscriber<Mqtt5Publish> {
    override fun transcribe(message: Mqtt5Publish): Result<MqttResponsePayload> = runCatching {
        with(message.payloadAsBytes) {
            val temperatures = Json.decodeFromString<SmallMatrixThermalData>(this.decodeToString())
            SmallMatrixThermalResponse(this, temperatures)
        }
    }
}