package org.alexcawl.iot_connector.client.ui.status_screen

sealed interface ClientStatusScreenAction {
    data object Refresh : ClientStatusScreenAction
}