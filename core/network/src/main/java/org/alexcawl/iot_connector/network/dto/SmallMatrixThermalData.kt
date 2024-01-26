package org.alexcawl.iot_connector.network.dto

import kotlinx.serialization.SerialName

data class SmallMatrixThermalData(
    @SerialName("device")
    val device: String,
    @SerialName("sensor_type")
    val sensorType: String,
    @SerialName("values")
    val temperatures: List<Int>
)