package org.alexcawl.iot_connector.ui.data

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class ProfileState(
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

