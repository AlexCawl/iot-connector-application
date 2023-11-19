package org.alexcawl.iot_connector.profile.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.pref.dao.ProfileDatastoreDao
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.profile.util.IProfileMapper
import java.util.UUID
import javax.inject.Inject

class ProfileService @Inject constructor(
    private val datastoreDao: ProfileDatastoreDao,
    private val databaseDao: ProfileDatabaseDao,
    private val mapper: IProfileMapper
) : IProfileService {
    override fun getAllProfiles(): Flow<List<Profile>> =
        databaseDao.getAllProfiles().map { entities ->
            entities.map { entity ->
                mapper.mapFirst(entity)
            }
        }

    override fun getProfile(id: UUID): Flow<Profile?> = databaseDao.getProfile(id).map { entity ->
        when (entity) {
            null -> null
            else -> mapper.mapFirst(entity)
        }
    }

    override suspend fun createProfile(model: Profile) =
        databaseDao.createProfile(mapper.mapSecond(model))

    override suspend fun updateProfile(model: Profile) =
        databaseDao.updateProfile(mapper.mapSecond(model))

    override suspend fun deleteProfile(model: Profile) =
        databaseDao.deleteProfile(mapper.mapSecond(model))

    override fun getSelectedProfileId(): Flow<UUID?> = datastoreDao.getSelectedProfileId()

    override suspend fun setSelectedProfileId(id: UUID?) = datastoreDao.setSelectedProfileId(id)
}