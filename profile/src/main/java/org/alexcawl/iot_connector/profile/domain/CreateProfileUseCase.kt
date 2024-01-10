package org.alexcawl.iot_connector.profile.domain

import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import org.alexcawl.iot_connector.ui.mappers.IProfileStateMapper
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