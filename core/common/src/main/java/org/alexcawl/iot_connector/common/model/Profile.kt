package org.alexcawl.iot_connector.common.model

import java.util.UUID

data class Profile(
    val id: UUID,
    val name: String,
    val createdAt: Long,
    val configuration: MQTTConfiguration,
    val info: String? = null,
    val changedAt: Long? = null
)
