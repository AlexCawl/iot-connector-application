package org.alexcawl.iot_connector.persistence.db.entities

data class MQTTConfigurationEntity(
    val host: String,
    val port: Int,
    val login: String? = null,
    val password: String? = null
)
