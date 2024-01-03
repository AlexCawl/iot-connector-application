package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.domain.IProfileService
import java.util.UUID
import javax.inject.Inject

class GetProfileByIdUseCase @Inject constructor(
    private val service: IProfileService
) {
    suspend operator fun invoke(profileID: UUID) = service.getProfile(profileID)
}