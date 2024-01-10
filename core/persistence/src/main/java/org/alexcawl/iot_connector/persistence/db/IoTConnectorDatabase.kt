package org.alexcawl.iot_connector.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.alexcawl.iot_connector.persistence.db.dao.ConnectionDatabaseDao
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.db.entities.ConnectionEntity
import org.alexcawl.iot_connector.persistence.db.entities.ProfileEntity

@Database(
    entities = [ProfileEntity::class, ConnectionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class IoTConnectorDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDatabaseDao

    abstract fun connectionDao(): ConnectionDatabaseDao
}