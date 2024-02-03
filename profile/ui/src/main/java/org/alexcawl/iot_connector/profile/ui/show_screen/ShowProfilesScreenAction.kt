package org.alexcawl.iot_connector.profile.ui.show_screen

import java.util.UUID

sealed interface ShowProfilesScreenAction {
    data class SelectProfileById(val id: UUID?) : ShowProfilesScreenAction
}