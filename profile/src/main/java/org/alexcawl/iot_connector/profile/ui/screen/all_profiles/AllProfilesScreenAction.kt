package org.alexcawl.iot_connector.profile.ui.screen.all_profiles

import java.util.UUID

sealed interface AllProfilesScreenAction {
    data class SelectProfileById(val id: UUID?) : AllProfilesScreenAction
}