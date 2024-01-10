package org.alexcawl.iot_connector.profile.domain.usecase

import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper
import org.alexcawl.iot_connector.ui.data.ProfileState
import java.util.UUID
import javax.inject.Inject

class UpdateProfileByIdUseCase @Inject constructor(
    private val repository: IProfileRepository,
    private val mapper: IProfileStateMapper
) {
    suspend operator fun invoke(profileID: UUID, data: ProfileState) = with(mapper) {
        val newProfile = mapFirst(data)
        when (val oldProfile = repository.getProfile(profileID)) {
            null -> repository.createProfile(newProfile)
            else -> {
                val updatedProfile = with(newProfile) {
                    oldProfile.copy(
                        name = name,
                        info = info,
                        host = host,
                        port = port,
                        login = login,
                        password = password,
                        changedAt = System.currentTimeMillis()
                    )
                }
                repository.updateProfile(updatedProfile)
            }
        }
    }
}