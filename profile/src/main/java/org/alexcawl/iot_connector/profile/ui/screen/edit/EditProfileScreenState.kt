package org.alexcawl.iot_connector.profile.ui.screen.edit

import org.alexcawl.iot_connector.common.model.Profile

sealed interface EditProfileScreenState {
    data object Initial : EditProfileScreenState

    data class Successful(val profile: Profile) : EditProfileScreenState

    data object ProfileNotFound : EditProfileScreenState
}