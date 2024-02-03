package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.data.repository.IProfileRepository
import org.alexcawl.iot_connector.profile.domain.mapper.IProfileStateMapper
import org.alexcawl.iot_connector.ui.state.ProfileState
import java.util.UUID
import javax.inject.Inject

class GetProfileByIdUseCase @Inject constructor(
    private val repository: IProfileRepository,
    private val mapper: IProfileStateMapper
) {
    suspend operator fun invoke(profileID: UUID): ProfileState? = with(mapper) {
        when (val profile = repository.getProfile(profileID)) {
            null -> null
            else -> mapSecond(profile)
        }
    }
}