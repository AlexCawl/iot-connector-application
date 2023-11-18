package org.alexcawl.iot_connector.profile.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDao
import org.alexcawl.iot_connector.profile.domain.IProfileDatasource
import org.alexcawl.iot_connector.profile.util.IProfileMapper
import java.util.UUID
import javax.inject.Inject

class ProfileDatasource @Inject constructor(
    private val dao: ProfileDao,
    private val mapper: IProfileMapper
) : IProfileDatasource {
    override fun getAllProfiles(): Flow<List<Profile>> = dao.getAllProfiles().map { entities ->
        entities.map { entity ->
            mapper.mapFirst(entity)
        }
    }

    override fun getProfile(id: UUID): Flow<Profile?> = dao.getProfile(id).map { entity ->
        when (entity) {
            null -> null
            else -> mapper.mapFirst(entity)
        }
    }

    override suspend fun createProfile(model: Profile) = dao.createProfile(mapper.mapSecond(model))

    override suspend fun updateProfile(model: Profile) = dao.updateProfile(mapper.mapSecond(model))

    override suspend fun deleteProfile(model: Profile) = dao.deleteProfile(mapper.mapSecond(model))
}