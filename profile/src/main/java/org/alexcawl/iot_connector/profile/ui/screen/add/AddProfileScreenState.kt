package org.alexcawl.iot_connector.profile.ui.screen.add

import org.alexcawl.iot_connector.common.model.Profile

sealed interface AddProfileScreenState {
    data object Initial : AddProfileScreenState

    data class Building(val profile: Profile) : AddProfileScreenState

    data object Completed : AddProfileScreenState
}