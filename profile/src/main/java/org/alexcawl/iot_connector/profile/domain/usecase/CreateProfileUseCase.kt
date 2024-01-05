package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.data.IProfileService
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper
import org.alexcawl.iot_connector.ui.data.ProfileState
import javax.inject.Inject

class CreateProfileUseCase @Inject constructor(
    private val service: IProfileService,
    private val mapper: IProfileStateMapper
) {
    suspend operator fun invoke(profile: ProfileState) = with(mapper) {
        service.createProfile(mapFirst(profile))
    }
}