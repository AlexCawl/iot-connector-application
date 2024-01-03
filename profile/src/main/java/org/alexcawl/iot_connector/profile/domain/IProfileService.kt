package org.alexcawl.iot_connector.profile.domain

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.Profile
import java.util.UUID

interface IProfileService {
    /*
    * Room usage
    * */
    fun getAllProfiles(): Flow<List<Profile>>

    suspend fun getProfile(id: UUID): Profile?

    suspend fun createProfile(profile: Profile)

    suspend fun updateProfile(id: UUID, profile: Profile)

    suspend fun deleteProfile(id: UUID)

    /*
    * LocalStorage usage
    * */
    fun getSelectedProfileId(): Flow<UUID?>

    suspend fun setSelectedProfileId(id: UUID?)
}