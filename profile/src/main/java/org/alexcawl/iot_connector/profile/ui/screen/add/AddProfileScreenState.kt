package org.alexcawl.iot_connector.profile.ui.screen.add

sealed interface AddProfileScreenState {
    data object Initial : AddProfileScreenState

    data class Building(
        val name: String = "",
        val nameMessage: Message = Message.OK,
        val info: String = "",
        val host: String = "",
        val hostMessage: Message = Message.OK,
        val port: String = "",
        val portMessage: Message = Message.OK,
        val login: String = "",
        val password: String = ""
    ) : AddProfileScreenState {
        enum class Message {
            OK,
            NULL,
            NOT_A_NUMBER
        }
    }

    data object Completed : AddProfileScreenState
}