package org.alexcawl.iot_connector.profile.domain

import org.alexcawl.iot_connector.common.model.ProfileModel
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper
import org.alexcawl.iot_connector.ui.data.ProfileState
import javax.inject.Inject

class ProfileStateMapper @Inject constructor() : IProfileStateMapper {
    override fun mapFirst(from: ProfileState): ProfileModel = with(from) {
        ProfileModel(
            id = id,
            name = name,
            host = host,
            port = port,
            createdAt = createdAt,
            login = login,
            password = password,
            info = info,
            changedAt = changedAt
        )
    }

    override fun mapSecond(from: ProfileModel): ProfileState = with(from) {
        ProfileState(
            id = id,
            name = name,
            host = host,
            port = port,
            createdAt = createdAt,
            login = login,
            password = password,
            info = info,
            changedAt = changedAt
        )
    }
}