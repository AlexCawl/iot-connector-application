package org.alexcawl.iot_connector.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey
    val id: UUID,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("created_at")
    val createdAt: Long,

    @Embedded(prefix = "conf_")
    val configuration: MQTTConfigurationEntity,

    @ColumnInfo("info")
    val info: String? = null,

    @ColumnInfo("changed_at")
    val changedAt: Long? = null
)

data class MQTTConfigurationEntity(
    val host: String,
    val port: Int,
    val login: String? = null,
    val password: String? = null
)