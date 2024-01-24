package org.alexcawl.iot_connector.connection.ui.screen.update

interface UpdateConnectionScreenAction {
    data class SetEndpoint(val endpoint: String): UpdateConnectionScreenAction

    data class SetName(val name: String): UpdateConnectionScreenAction

    data class SetNameType(val optional: Boolean) : UpdateConnectionScreenAction
}