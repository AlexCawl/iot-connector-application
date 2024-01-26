package org.alexcawl.iot_connector.network.dto

import org.alexcawl.iot_connector.common.model.MqttResponsePayload

data class TextResponse internal constructor(
    override val raw: ByteArray,
    val text: String
) : MqttResponsePayload {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextResponse

        if (text != other.text) return false
        if (!raw.contentEquals(other.raw)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + raw.contentHashCode()
        return result
    }
}