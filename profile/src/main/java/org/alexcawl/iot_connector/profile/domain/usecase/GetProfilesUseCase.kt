package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.alexcawl.iot_connector.ui.data.ProfileState
import javax.inject.Inject

class GetProfilesUseCase @Inject constructor(
    val getAvailableProfiles: GetAvailableProfilesUseCase,
    val getSelectedProfile: GetSelectedProfileUseCase,
) {
    operator fun invoke(): Flow<Pair<ProfileState?, List<ProfileState>>> =
        getAvailableProfiles().combine(getSelectedProfile()) { profiles: List<ProfileState>, selectedProfile: ProfileState? ->
            Pair(selectedProfile, profiles)
        }
}