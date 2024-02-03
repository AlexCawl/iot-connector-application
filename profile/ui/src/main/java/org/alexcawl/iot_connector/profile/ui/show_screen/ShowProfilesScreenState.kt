package org.alexcawl.iot_connector.profile.ui.show_screen

import org.alexcawl.iot_connector.ui.state.ProfileState

sealed interface ShowProfilesScreenState {
    data object Initial : ShowProfilesScreenState

    data class Viewing(
        val selectedProfile: ProfileState?,
        val availableProfiles: List<ProfileState>
    ) : ShowProfilesScreenState
}