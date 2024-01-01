package org.alexcawl.iot_connector.profile.ui.screen.add

sealed interface AddProfileScreenAction {
    data class SetName(val name: String): AddProfileScreenAction

    data class SetInfo(val info: String): AddProfileScreenAction

    data class SetHost(val host: String) : AddProfileScreenAction

    data class SetPort(val port: String) : AddProfileScreenAction

    data class SetLogin(val login: String) : AddProfileScreenAction

    data class SetPassword(val password: String) : AddProfileScreenAction

    data object AddProfile : AddProfileScreenAction
}