package org.alexcawl.iot_connector.profile.domain

import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import org.alexcawl.iot_connector.ui.mappers.IProfileStateMapper
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