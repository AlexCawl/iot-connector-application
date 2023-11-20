package org.alexcawl.iot_connector.profile.ui.screen.add

sealed interface AddProfileScreenAction {
    data class SetProfileName(val name: String): AddProfileScreenAction

    data class SetProfileInfo(val info: String): AddProfileScreenAction


}