package org.alexcawl.iot_connector.profile.domain

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.Profile
import java.util.UUID

interface IProfileDatasource {
    fun getAllProfiles(): Flow<List<Profile>>

    fun getProfile(id: UUID): Flow<Profile?>

    suspend fun createProfile(model: Profile)

    suspend fun updateProfile(model: Profile)

    suspend fun deleteProfile(model: Profile)
}