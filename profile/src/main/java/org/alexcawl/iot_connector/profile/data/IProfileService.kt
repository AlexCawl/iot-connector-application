package org.alexcawl.iot_connector.profile.data

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.ProfileModel
import java.util.UUID

interface IProfileService {
    /*
    * Room usage
    * */
    fun getAllProfiles(): Flow<List<ProfileModel>>

    suspend fun getProfile(id: UUID): ProfileModel?

    suspend fun createProfile(profile: ProfileModel)

    suspend fun updateProfile(id: UUID, profile: ProfileModel)

    suspend fun deleteProfile(id: UUID)

    /*
    * LocalStorage usage
    * */
    fun getSelectedProfileId(): Flow<UUID?>

    suspend fun setSelectedProfileId(id: UUID?)
}