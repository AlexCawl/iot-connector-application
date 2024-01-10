package org.alexcawl.iot_connector.ui.data

import java.util.UUID

data class ConnectionState(
    val id: UUID,
    val endpoint: String,
    val name: String?,
    val available: Boolean,
    val mappable: Boolean
)
