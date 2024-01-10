package org.alexcawl.iot_connector.persistence.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.model.ProfileModel
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import org.alexcawl.iot_connector.persistence.mappers.IProfileEntityMapper
import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import java.util.UUID
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val databaseDao: ProfileDatabaseDao,
    private val datastoreDao: ProfileDatastoreDao,
    private val mapper: IProfileEntityMapper
) : IProfileRepository {
    override fun getProfilesAsFlow(): Flow<List<ProfileModel>> =
        databaseDao.getProfiles().map { entities ->
            entities.map { entity ->
                mapper.mapFirst(entity)
            }
        }

    override fun getSelectedProfileAsFlow(): Flow<ProfileModel?> =
        datastoreDao.getSelectedProfileId().map { selectedId ->
            selectedId?.let {
                databaseDao.getProfile(selectedId)?.let { selectedProfile ->
                    mapper.mapFirst(selectedProfile)
                }
            }
        }

    override suspend fun getProfile(id: UUID): ProfileModel? = databaseDao.getProfile(id)?.let {
        mapper.mapFirst(it)
    }

    override suspend fun createProfile(model: ProfileModel) =
        databaseDao.createProfile(mapper.mapSecond(model))

    override suspend fun updateProfile(model: ProfileModel) =
        databaseDao.updateProfile(mapper.mapSecond(model))

    override suspend fun deleteProfile(id: UUID) =
        when (val profileToDelete = databaseDao.getProfile(id)) {
            null -> Unit
            else -> databaseDao.deleteProfile(profileToDelete)
        }

    override suspend fun selectProfileById(id: UUID?) = datastoreDao.setSelectedProfileId(id)
}