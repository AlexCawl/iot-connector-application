package org.alexcawl.iot_connector.network.dto

import org.alexcawl.iot_connector.common.model.MqttResponsePayload

data class LargeMatrixThermalResponse internal constructor(
    override val raw: ByteArray,
    val temperatures: LargeMatrixThermalData
) : MqttResponsePayload {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LargeMatrixThermalResponse

        if (!raw.contentEquals(other.raw)) return false
        if (temperatures != other.temperatures) return false

        return true
    }

    override fun hashCode(): Int {
        var result = raw.contentHashCode()
        result = 31 * result + temperatures.hashCode()
        return result
    }
}

