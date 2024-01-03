package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.profile.domain.IProfileService
import javax.inject.Inject

class CreateProfileUseCase @Inject constructor(
    private val service: IProfileService
) {
    suspend operator fun invoke(profile: Profile) = service.createProfile(profile)
}