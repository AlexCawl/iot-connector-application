package org.alexcawl.iot_connector.profile.ui.screen.update.add

import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState

sealed interface AddProfileScreenState {
    data object Initial : AddProfileScreenState

    data class Building(val profileScreenState: ProfileScreenState) : AddProfileScreenState

    data object Completed : AddProfileScreenState
}