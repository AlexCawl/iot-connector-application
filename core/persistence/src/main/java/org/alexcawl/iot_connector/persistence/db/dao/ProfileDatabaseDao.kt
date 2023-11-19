package org.alexcawl.iot_connector.persistence.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.persistence.db.entities.ProfileEntity
import java.util.UUID

@Dao
interface ProfileDatabaseDao {
    @Query("SELECT * FROM profiles")
    fun getAllProfiles(): Flow<List<ProfileEntity>>

    @Query("SELECT * FROM profiles WHERE id = :id")
    fun getProfile(id: UUID): Flow<ProfileEntity?>

    @Insert(entity = ProfileEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun createProfile(profileEntity: ProfileEntity)

    @Update(entity = ProfileEntity::class)
    suspend fun updateProfile(profileEntity: ProfileEntity)

    @Delete(entity = ProfileEntity::class)
    suspend fun deleteProfile(profileEntity: ProfileEntity)
}