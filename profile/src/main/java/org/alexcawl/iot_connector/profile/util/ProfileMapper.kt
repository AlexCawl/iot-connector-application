package org.alexcawl.iot_connector.profile.util

import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.persistence.db.entities.MQTTConfigurationEntity
import org.alexcawl.iot_connector.persistence.db.entities.ProfileEntity
import javax.inject.Inject

class ProfileMapper @Inject constructor() : IProfileMapper {
    override fun mapFirst(from: ProfileEntity): Profile = with(from) {
        Profile(
            id = id,
            name = name,
            host = configuration.host,
            port = configuration.port,
            createdAt = createdAt,
            login = configuration.login,
            password = configuration.password,
            info = info,
            changedAt = changedAt
        )
    }

    override fun mapSecond(from: Profile): ProfileEntity = with(from) {
        ProfileEntity(
            id = id,
            name = name,
            createdAt = createdAt,
            configuration = MQTTConfigurationEntity(
                host = host,
                port = port,
                login = login,
                password = password
            ),
            info = info,
            changedAt = changedAt
        )
    }
}