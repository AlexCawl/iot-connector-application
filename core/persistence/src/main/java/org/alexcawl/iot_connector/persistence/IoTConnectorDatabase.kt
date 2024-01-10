package org.alexcawl.iot_connector.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.entity.ConnectionEntity
import org.alexcawl.iot_connector.persistence.entity.ProfileEntity

@Database(
    entities = [ProfileEntity::class, ConnectionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class IoTConnectorDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDatabaseDao

    abstract fun connectionDao(): ConnectionDatabaseDao
}