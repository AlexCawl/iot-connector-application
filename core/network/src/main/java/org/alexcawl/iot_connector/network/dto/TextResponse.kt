package org.alexcawl.iot_connector.network.dto

import org.alexcawl.iot_connector.common.model.MqttResponsePayload

data class TextResponse internal constructor(
    val text: String
) : MqttResponsePayload {
    override val raw: ByteArray
        get() = text.toByteArray()
}