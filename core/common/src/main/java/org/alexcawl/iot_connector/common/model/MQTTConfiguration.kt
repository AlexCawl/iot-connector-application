package org.alexcawl.iot_connector.common.model

data class MQTTConfiguration(
    val host: String,
    val port: Int,
    val login: String? = null,
    val password: String? = null
)