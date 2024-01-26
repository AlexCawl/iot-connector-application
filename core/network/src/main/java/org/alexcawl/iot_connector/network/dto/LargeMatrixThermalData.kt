package org.alexcawl.iot_connector.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LargeMatrixThermalData(
    @SerialName("device")
    val device: String,
    @SerialName("sensor_type")
    val sensorType: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("values")
    val temperatures: List<Int>
)