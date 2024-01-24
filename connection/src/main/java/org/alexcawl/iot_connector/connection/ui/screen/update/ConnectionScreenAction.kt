package org.alexcawl.iot_connector.connection.ui.screen.update

interface ConnectionScreenAction {
    data class SetEndpoint(val endpoint: String): ConnectionScreenAction

    data class SetName(val name: String): ConnectionScreenAction

    data class SetNameType(val optional: Boolean) : ConnectionScreenAction
}