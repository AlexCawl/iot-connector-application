package org.alexcawl.iot_connector.persistence.entity

data class MQTTConfigurationHolder(
    val host: String,
    val port: Int,
    val login: String? = null,
    val password: String? = null
)