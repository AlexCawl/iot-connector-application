package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.profile.domain.IProfileService
import javax.inject.Inject

class GetAllProfilesUseCase @Inject constructor(
    private val service: IProfileService
) {
    operator fun invoke(): Flow<List<Profile>> = service.getAllProfiles()
}