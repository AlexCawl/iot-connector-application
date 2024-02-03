package org.alexcawl.iot_connector.connection.ui.update_screen

interface UpdateConnectionScreenState {
    data object Initial : UpdateConnectionScreenState

    data class Builder(
        val endpoint: String = "",
        val endpointMessage: Message = Message.OK,
        val name: String = "",
        val nameOptional: Boolean = true
    ) : UpdateConnectionScreenState {
        enum class Message {
            OK,
            EMPTY,
            WRONG_PATTERN
        }
    }

    data object Saving : UpdateConnectionScreenState
}