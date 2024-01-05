package org.alexcawl.iot_connector.profile.ui.screen.show

import org.alexcawl.iot_connector.common.model.Profile

sealed interface ShowProfilesScreenState {
    data object Initial : ShowProfilesScreenState

    data class Viewing(
        val selectedProfile: Profile?,
        val availableProfiles: List<Profile>
    ) : ShowProfilesScreenState
}