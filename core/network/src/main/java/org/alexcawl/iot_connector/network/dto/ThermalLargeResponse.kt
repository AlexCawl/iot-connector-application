package org.alexcawl.iot_connector.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import kotlin.jvm.Throws

@Serializable
data class ThermalLargeResponse(
    @SerialName("device")
    val device: String,
    @SerialName("sensor_type")
    val sensorType: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("values")
    val temperatures: List<Int>
) : MqttResponsePayload {
    override val raw: ByteArray
        @Throws(IllegalStateException::class)
        get() = throw IllegalStateException("Byte array used only for default implementation!")
}