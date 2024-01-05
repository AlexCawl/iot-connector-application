package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.profile.domain.IProfileService
import java.util.UUID
import javax.inject.Inject

class GetSelectedProfileIdUseCase @Inject constructor(
    private val service: IProfileService
) {
    operator fun invoke(): Flow<UUID?> = service.getSelectedProfileId()
}