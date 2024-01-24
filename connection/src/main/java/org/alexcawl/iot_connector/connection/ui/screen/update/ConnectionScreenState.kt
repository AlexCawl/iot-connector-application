package org.alexcawl.iot_connector.connection.ui.screen.update

interface ConnectionScreenState {
    data object Initial : ConnectionScreenState

    data class Builder(
        val endpoint: String = "",
        val endpointMessage: Message = Message.OK,
        val name: String = "",
        val nameOptional: Boolean = true
    ) : ConnectionScreenState {
        enum class Message {
            OK,
            EMPTY,
            WRONG_PATTERN
        }
    }

    data object Saving : ConnectionScreenState
}