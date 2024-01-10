package org.alexcawl.iot_connector.profile.domain.usecases

import org.alexcawl.iot_connector.profile.domain.IProfileRepository
import org.alexcawl.iot_connector.profile.domain.IProfileStateMapper
import org.alexcawl.iot_connector.ui.state.ProfileState
import javax.inject.Inject

class CreateProfileUseCase @Inject constructor(
    private val repository: IProfileRepository,
    private val mapper: IProfileStateMapper
) {
    suspend operator fun invoke(profile: ProfileState) = with(mapper) {
        repository.createProfile(mapFirst(profile))
    }
}