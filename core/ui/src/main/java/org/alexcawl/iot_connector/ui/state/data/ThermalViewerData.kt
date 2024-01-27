package org.alexcawl.iot_connector.ui.state.data

import androidx.compose.runtime.Immutable

@Immutable
data class ThermalViewerData(
    val device: String,
    val sensorType: String,
    val temperatures: Array<Array<Int>>
) : ViewerData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThermalViewerData

        if (device != other.device) return false
        if (sensorType != other.sensorType) return false
        if (!temperatures.contentDeepEquals(other.temperatures)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = device.hashCode()
        result = 31 * result + sensorType.hashCode()
        result = 31 * result + temperatures.contentDeepHashCode()
        return result
    }
}