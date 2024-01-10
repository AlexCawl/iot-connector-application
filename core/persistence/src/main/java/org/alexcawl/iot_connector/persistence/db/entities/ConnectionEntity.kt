package org.alexcawl.iot_connector.persistence.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "connections")
data class ConnectionEntity(
    @PrimaryKey
    val id: UUID,

    @ColumnInfo("endpoint")
    val endpoint: String,

    @ColumnInfo("name")
    val name: String?
)