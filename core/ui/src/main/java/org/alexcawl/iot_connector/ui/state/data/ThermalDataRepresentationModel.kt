package org.alexcawl.iot_connector.ui.state.data

import androidx.compose.runtime.Immutable

@Immutable
data class ThermalDataRepresentationModel(
    val device: String,
    val sensorType: String,
    val temperatures: Array<Float>
) : ViewerDataRepresentationModel {
    override val priority: Int
        get() = 1

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThermalDataRepresentationModel

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