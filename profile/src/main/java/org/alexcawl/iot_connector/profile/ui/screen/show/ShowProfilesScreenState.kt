package org.alexcawl.iot_connector.profile.ui.screen.show

import org.alexcawl.iot_connector.ui.data.ProfileState

sealed interface ShowProfilesScreenState {
    data object Initial : ShowProfilesScreenState

    data class Viewing(
        val selectedProfile: ProfileState?,
        val availableProfiles: List<ProfileState>
    ) : ShowProfilesScreenState
}