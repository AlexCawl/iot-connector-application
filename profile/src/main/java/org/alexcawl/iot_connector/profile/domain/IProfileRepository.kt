package org.alexcawl.iot_connector.profile.domain

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.ProfileModel
import java.util.UUID

interface IProfileRepository {
    fun getProfilesAsFlow(): Flow<List<ProfileModel>>

    fun getSelectedProfileAsFlow(): Flow<ProfileModel?>

    suspend fun getProfile(id: UUID): ProfileModel?

    suspend fun createProfile(model: ProfileModel)

    suspend fun updateProfile(model: ProfileModel)

    suspend fun deleteProfile(id: UUID)

    suspend fun selectProfileById(id: UUID?)
}