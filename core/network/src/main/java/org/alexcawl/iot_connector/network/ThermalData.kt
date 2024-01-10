package org.alexcawl.iot_connector.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThermalData(
    @SerialName("device")
    val device: String,
    @SerialName("sensor_type")
    val sensorType: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("values")
    val temperatures: List<Int>
)
