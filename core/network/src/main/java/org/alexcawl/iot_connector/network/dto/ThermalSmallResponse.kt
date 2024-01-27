package org.alexcawl.iot_connector.network.dto

import kotlinx.serialization.SerialName
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import kotlin.jvm.Throws

data class ThermalSmallResponse(
    @SerialName("device")
    val device: String,
    @SerialName("sensor_type")
    val sensorType: String,
    @SerialName("values")
    val temperatures: List<Int>
) : MqttResponsePayload {
    override val raw: ByteArray
        @Throws(IllegalStateException::class)
        get() = throw IllegalStateException("Byte array used only for default implementation!")
}