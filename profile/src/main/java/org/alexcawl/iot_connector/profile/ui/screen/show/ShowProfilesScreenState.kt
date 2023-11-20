package org.alexcawl.iot_connector.profile.ui.screen.show

import org.alexcawl.iot_connector.common.model.Profile
import java.util.UUID

sealed interface ShowProfilesScreenState {
    data object Initial : ShowProfilesScreenState

    data class Successful(
        val profiles: List<Profile>,
        val selectedProfileId: UUID?
    ) : ShowProfilesScreenState

    data class Fail(val cause: String) : ShowProfilesScreenState
}