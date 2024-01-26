package org.alexcawl.iot_connector.common.model

data class MqttResponseHolder(
    val params: Map<String, String>,
    val payloads: Set<MqttResponsePayload>
)
