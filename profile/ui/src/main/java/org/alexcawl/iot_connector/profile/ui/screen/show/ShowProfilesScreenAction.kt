package org.alexcawl.iot_connector.profile.ui.screen.show

import java.util.UUID

sealed interface ShowProfilesScreenAction {
    data class SelectProfileById(val id: UUID?) : ShowProfilesScreenAction
}