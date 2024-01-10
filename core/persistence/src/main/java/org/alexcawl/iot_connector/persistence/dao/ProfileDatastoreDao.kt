package org.alexcawl.iot_connector.persistence.dao

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ProfileDatastoreDao {
    fun getSelectedProfileId(): Flow<UUID?>

    suspend fun setSelectedProfileId(id: UUID?)
}