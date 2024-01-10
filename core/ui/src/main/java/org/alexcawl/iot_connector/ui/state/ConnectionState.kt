package org.alexcawl.iot_connector.ui.state

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class ConnectionState(
    val id: UUID,
    val endpoint: String,
    val name: String?,
    val available: Boolean,
    val mappable: Boolean
)
