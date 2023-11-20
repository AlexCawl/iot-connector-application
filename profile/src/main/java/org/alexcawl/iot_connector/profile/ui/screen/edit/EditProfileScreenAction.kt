package org.alexcawl.iot_connector.profile.ui.screen.edit

import java.util.UUID

sealed interface EditProfileScreenAction {
    data class InstallProfileById(val id: UUID) : EditProfileScreenAction
}