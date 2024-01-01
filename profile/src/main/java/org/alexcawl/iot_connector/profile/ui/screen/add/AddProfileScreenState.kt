package org.alexcawl.iot_connector.profile.ui.screen.add

sealed interface AddProfileScreenState {
    data object Initial : AddProfileScreenState

    data class Building(
        val name: String? = null,
        val nameMessage: Message = Message.OK,
        val info: String? = null,
        val host: String? = null,
        val hostMessage: Message = Message.OK,
        val port: String? = null,
        val portMessage: Message = Message.OK,
        val login: String? = null,
        val password: String? = null
    ) : AddProfileScreenState {
        enum class Message {
            OK,
            NULL,
            NOT_A_NUMBER
        }
    }

    data object Completed : AddProfileScreenState
}