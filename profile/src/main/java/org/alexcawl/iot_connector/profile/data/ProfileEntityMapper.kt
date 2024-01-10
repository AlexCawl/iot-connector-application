package org.alexcawl.iot_connector.profile.data

import org.alexcawl.iot_connector.common.model.ProfileModel
import org.alexcawl.iot_connector.persistence.entity.MQTTConfigurationEntity
import org.alexcawl.iot_connector.persistence.entity.ProfileEntity
import org.alexcawl.iot_connector.profile.domain.IProfileEntityMapper
import javax.inject.Inject

class ProfileEntityMapper @Inject constructor() : IProfileEntityMapper {
    override fun mapFirst(from: ProfileEntity): ProfileModel = with(from) {
        ProfileModel(
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

    override fun mapSecond(from: ProfileModel): ProfileEntity = with(from) {
        ProfileEntity(
            id = id, name = name, createdAt = createdAt, configuration = MQTTConfigurationEntity(
                host = host, port = port, login = login, password = password
            ), info = info, changedAt = changedAt
        )
    }
}