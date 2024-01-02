package org.alexcawl.iot_connector.profile.ui.screen.update.edit

import java.util.UUID

sealed interface EditProfileScreenAction {
    data class SelectProfileById(val id: UUID) : EditProfileScreenAction

    data class SetName(val name: String): EditProfileScreenAction

    data class SetInfo(val info: String): EditProfileScreenAction

    data class SetInfoType(val optional: Boolean) : EditProfileScreenAction

    data class SetHost(val host: String) : EditProfileScreenAction

    data class SetPort(val port: String) : EditProfileScreenAction

    data class SetLogin(val login: String) : EditProfileScreenAction

    data class SetLoginType(val optional: Boolean) : EditProfileScreenAction

    data class SetPassword(val password: String) : EditProfileScreenAction

    data class SetPasswordType(val optional: Boolean) : EditProfileScreenAction

    data object DeleteProfile : EditProfileScreenAction

    data object EditProfile : EditProfileScreenAction
}