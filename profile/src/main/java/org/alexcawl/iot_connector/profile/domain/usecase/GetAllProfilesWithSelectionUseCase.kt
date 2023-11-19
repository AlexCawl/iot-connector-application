package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.profile.domain.IProfileService
import java.util.UUID
import javax.inject.Inject
import kotlin.jvm.Throws

class GetAllProfilesWithSelectionUseCase @Inject constructor(
    private val service: IProfileService
) {
    operator fun invoke(): Flow<Pair<UUID?, List<Profile>>> = service.getAllProfiles()
        .combine(service.getSelectedProfileId()) { profiles: List<Profile>, id: UUID? ->
            when (id) {
                null -> Pair(null, profiles)
                else -> {
                    try {
                        validateSelectedProfileId(profiles, id)
                        Pair(id, profiles)
                    } catch (exception: IllegalArgumentException) {
                        service.setSelectedProfileId(null)
                        Pair(null, profiles)
                    }
                }
            }
        }

    @Throws(IllegalArgumentException::class)
    private fun validateSelectedProfileId(profiles: List<Profile>, id: UUID) {
        profiles.find { it.id == id } ?: throw IllegalArgumentException()
    }
}