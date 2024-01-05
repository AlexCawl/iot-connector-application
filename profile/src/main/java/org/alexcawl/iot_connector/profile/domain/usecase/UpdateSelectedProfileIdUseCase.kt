package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.data.IProfileService
import java.util.UUID
import javax.inject.Inject

class UpdateSelectedProfileIdUseCase @Inject constructor(
    val service: IProfileService
) {
    suspend operator fun invoke(selectedID: UUID?, newSelectedID: UUID?) = when (selectedID) {
        newSelectedID -> service.setSelectedProfileId(null)
        else -> service.setSelectedProfileId(newSelectedID)
    }
}