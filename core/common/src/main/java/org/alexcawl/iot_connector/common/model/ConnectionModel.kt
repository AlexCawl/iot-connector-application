package org.alexcawl.iot_connector.common.model

import java.util.UUID

data class ConnectionModel(
    val id: UUID,
    val endpoint: String,
    val name: String?
)