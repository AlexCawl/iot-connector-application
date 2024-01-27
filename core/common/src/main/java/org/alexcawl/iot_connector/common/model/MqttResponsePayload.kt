package org.alexcawl.iot_connector.common.model

import kotlin.jvm.Throws

interface MqttResponsePayload {
    @get:Throws(IllegalStateException::class)
    val raw: ByteArray
}