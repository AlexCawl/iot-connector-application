package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.data.repository.IProfileRepository
import java.util.UUID
import javax.inject.Inject

class DeleteProfileByIdUseCase @Inject constructor(
    private val repository: IProfileRepository
) {
    suspend operator fun invoke(profileID: UUID) = repository.deleteProfile(profileID)
}