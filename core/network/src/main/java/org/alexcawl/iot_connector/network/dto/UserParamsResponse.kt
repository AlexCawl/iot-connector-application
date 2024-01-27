package org.alexcawl.iot_connector.network.dto

import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import kotlin.jvm.Throws

data class UserParamsResponse(
    val params: Map<String, String>
) : MqttResponsePayload {
    override val raw: ByteArray
        @Throws(IllegalStateException::class)
        get() = throw IllegalStateException("Byte array used only for default implementation!")
}