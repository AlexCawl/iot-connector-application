package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.profile.data.IProfileService
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper
import org.alexcawl.iot_connector.ui.data.ProfileState
import java.util.UUID
import javax.inject.Inject

class UpdateProfileByIdUseCase @Inject constructor(
    private val service: IProfileService,
    private val mapper: IProfileStateMapper
) {
    suspend operator fun invoke(profileID: UUID, data: ProfileState) = with(mapper) {
        service.updateProfile(
            profileID,
            mapFirst(data)
        )
    }
}