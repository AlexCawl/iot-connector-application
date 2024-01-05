package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.profile.data.IProfileService
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper
import org.alexcawl.iot_connector.ui.data.ProfileState
import javax.inject.Inject

class GetAllProfilesUseCase @Inject constructor(
    private val service: IProfileService,
    private val mapper: IProfileStateMapper
) {
    operator fun invoke(): Flow<List<ProfileState>> = with(mapper) {
        service.getAllProfiles().map { profiles -> profiles.map(::mapSecond) }
    }
}