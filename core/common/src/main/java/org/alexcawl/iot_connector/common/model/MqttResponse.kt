package org.alexcawl.iot_connector.common.model

import java.util.UUID

data class MqttResponse(
    val id: UUID,
    val endpoint: String,
    val name: String? = null,
    val payloads: List<MqttResponsePayload>
)
