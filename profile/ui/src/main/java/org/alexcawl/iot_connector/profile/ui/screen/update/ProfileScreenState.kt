package org.alexcawl.iot_connector.profile.ui.screen.update

interface ProfileScreenState {
    data object Initial : ProfileScreenState

    data class Builder(
        val name: String = "",
        val nameMessage: Message = Message.OK,
        val info: String = "",
        val infoOptional: Boolean = true,
        val host: String = "",
        val hostMessage: Message = Message.OK,
        val port: String = "",
        val portMessage: Message = Message.OK,
        val login: String = "",
        val loginOptional: Boolean = true,
        val password: String = "",
        val passwordOptional: Boolean = true
    ) : ProfileScreenState {
        enum class Message {
            OK,
            NULL,
            NOT_A_NUMBER
        }
    }

    data object Saving : ProfileScreenState
}

