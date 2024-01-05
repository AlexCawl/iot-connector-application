package org.alexcawl.iot_connector.profile.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.model.ProfileModel
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.pref.dao.ProfileDatastoreDao
import org.alexcawl.iot_connector.persistence.util.IProfileEntityMapper
import java.util.UUID
import javax.inject.Inject

class ProfileService @Inject constructor(
    private val datastoreDao: ProfileDatastoreDao,
    private val databaseDao: ProfileDatabaseDao,
    private val mapper: IProfileEntityMapper
) : IProfileService {
    override fun getAllProfiles(): Flow<List<ProfileModel>> = databaseDao.getProfiles().map { entities ->
        entities.map { entity ->
            mapper.mapFirst(entity)
        }
    }

    override suspend fun getProfile(id: UUID): ProfileModel? =
        when (val entity = databaseDao.getProfile(id)) {
            null -> null
            else -> mapper.mapFirst(entity)
        }

    override suspend fun createProfile(profile: ProfileModel) =
        databaseDao.createProfile(mapper.mapSecond(profile))

    override suspend fun updateProfile(id: UUID, profile: ProfileModel) {
        when (val entity = databaseDao.getProfile(id)) {
            null -> databaseDao.createProfile(mapper.mapSecond(profile))
            else -> {
                val existingProfile = mapper.mapFirst(entity)
                val mergedProfile = with(profile) {
                    existingProfile.copy(
                        name = name,
                        info = info,
                        host = host,
                        port = port,
                        login = login,
                        password = password,
                        changedAt = System.currentTimeMillis()
                    )
                }
                databaseDao.updateProfile(mapper.mapSecond(mergedProfile))
            }
        }
    }

    override suspend fun deleteProfile(id: UUID) {
        when (val entity = databaseDao.getProfile(id)) {
            null -> Unit
            else -> databaseDao.deleteProfile(entity)
        }
    }

    override fun getSelectedProfileId(): Flow<UUID?> = datastoreDao.getSelectedProfileId()

    override suspend fun setSelectedProfileId(id: UUID?) = datastoreDao.setSelectedProfileId(id)
}