package org.alexcawl.iot_connector.common.model

import java.util.UUID

data class Profile(
    val id: UUID,
    val name: String,
    val host: String,
    val port: Int,
    val createdAt: Long,
    val login: String? = null,
    val password: String? = null,
    val info: String? = null,
    val changedAt: Long? = null
)
