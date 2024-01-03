package org.alexcawl.iot_connector.profile.ui.screen.update

interface ProfileScreenAction {
    data class SetName(val name: String): ProfileScreenAction

    data class SetInfo(val info: String): ProfileScreenAction

    data class SetInfoType(val optional: Boolean) : ProfileScreenAction

    data class SetHost(val host: String) : ProfileScreenAction

    data class SetPort(val port: String) : ProfileScreenAction

    data class SetLogin(val login: String) : ProfileScreenAction

    data class SetLoginType(val optional: Boolean) : ProfileScreenAction

    data class SetPassword(val password: String) : ProfileScreenAction

    data class SetPasswordType(val optional: Boolean) : ProfileScreenAction

    data object Save : ProfileScreenAction
}