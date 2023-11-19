package org.alexcawl.iot_connector.profile.ui.screen.all_profiles

import org.alexcawl.iot_connector.common.model.Profile
import java.util.UUID

sealed interface AllProfilesScreenState {
    data object Initial : AllProfilesScreenState

    data class Successful(
        val profiles: List<Profile>,
        val selectedProfileId: UUID?
    ) : AllProfilesScreenState

    data class Fail(val cause: String) : AllProfilesScreenState
}