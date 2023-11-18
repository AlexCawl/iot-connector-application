package org.alexcawl.iot_connector.profile.util

import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.persistence.db.entities.ProfileEntity
import javax.inject.Inject

class ProfileMapper @Inject constructor(
    private val configurationMapper: IMQTTConfigurationMapper
) : IProfileMapper {
    override fun mapFirst(from: ProfileEntity): Profile = with(from) {
        Profile(
            id,
            name,
            createdAt,
            configurationMapper.mapFirst(configuration),
            info,
            changedAt
        )
    }

    override fun mapSecond(from: Profile): ProfileEntity = with(from) {
        ProfileEntity(
            id,
            name,
            createdAt,
            configurationMapper.mapSecond(configuration),
            info,
            changedAt
        )
    }
}