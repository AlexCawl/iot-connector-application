package org.alexcawl.iot_connector.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.iot_connector.common.model.MqttResponsePayload

@Serializable
data class ThermalResponse(
    @SerialName("device")
    val device: String,
    @SerialName("sensorType")
    val sensorType: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("values")
    val temperatures: List<Int>
) : MqttResponsePayload {
    override val raw: String
        get() = this.toString()
}