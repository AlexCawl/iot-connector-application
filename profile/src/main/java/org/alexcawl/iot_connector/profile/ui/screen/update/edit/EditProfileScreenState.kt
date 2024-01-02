package org.alexcawl.iot_connector.profile.ui.screen.update.edit

import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState

sealed interface EditProfileScreenState {
    data object Initial : EditProfileScreenState

    data class Building(val profileScreenState: ProfileScreenState) : EditProfileScreenState

    data object ProfileNotFound : EditProfileScreenState

    data object Completed : EditProfileScreenState
}