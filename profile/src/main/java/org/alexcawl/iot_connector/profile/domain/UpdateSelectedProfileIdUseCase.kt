package org.alexcawl.iot_connector.profile.domain

import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import java.util.UUID
import javax.inject.Inject

class UpdateSelectedProfileIdUseCase @Inject constructor(
    private val repository: IProfileRepository
) {
    suspend operator fun invoke(selectedID: UUID?, newSelectedID: UUID?) = when (selectedID) {
        newSelectedID -> repository.selectProfileById(null)
        else -> repository.selectProfileById(newSelectedID)
    }
}