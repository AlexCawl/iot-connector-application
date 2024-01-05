package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.alexcawl.iot_connector.ui.data.ProfileState
import java.util.UUID
import javax.inject.Inject
import kotlin.jvm.Throws

class GetProfilesUseCase @Inject constructor(
    val getAllProfiles: GetAllProfilesUseCase,
    val getSelectedID: GetSelectedProfileIdUseCase,
    val updateSelectedID: UpdateSelectedProfileIdUseCase
) {
    operator fun invoke(): Flow<Pair<ProfileState?, List<ProfileState>>> =
        getAllProfiles().combine(getSelectedID()) { profiles: List<ProfileState>, selectedID: UUID? ->
            when (selectedID) {
                null -> Pair(null, profiles)
                else -> {
                    try {
                        val selectedProfile = validateSelectedProfileId(profiles, selectedID)
                        Pair(selectedProfile, profiles)
                    } catch (exception: IllegalArgumentException) {
                        updateSelectedID(selectedID, null)
                        Pair(null, profiles)
                    }
                }
            }
        }

    @Throws(IllegalArgumentException::class)
    private fun validateSelectedProfileId(profiles: List<ProfileState>, id: UUID): ProfileState {
        return profiles.find { it.id == id } ?: throw IllegalArgumentException()
    }
}