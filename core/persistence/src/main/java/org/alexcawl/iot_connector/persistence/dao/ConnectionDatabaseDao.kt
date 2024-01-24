package org.alexcawl.iot_connector.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.persistence.entity.ConnectionEntity
import java.util.UUID

@Dao
interface ConnectionDatabaseDao {
    @Query("SELECT * from connections")
    fun getConnections(): Flow<List<ConnectionEntity>>

    @Query("SELECT * FROM connections WHERE id = :id")
    suspend fun getConnection(id: UUID): ConnectionEntity?

    @Insert(entity = ConnectionEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun createConnection(connectionEntity: ConnectionEntity)

    @Update(entity = ConnectionEntity::class)
    suspend fun updateConnection(connectionEntity: ConnectionEntity)

    @Delete(entity = ConnectionEntity::class)
    suspend fun deleteConnection(connectionEntity: ConnectionEntity)
}