package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.domain.IProfileService
import java.util.UUID
import javax.inject.Inject

class SetSelectedProfileByIdUseCase @Inject constructor(
    private val service: IProfileService
) {
    suspend operator fun invoke(id: UUID?) = service.setSelectedProfileId(id)
}