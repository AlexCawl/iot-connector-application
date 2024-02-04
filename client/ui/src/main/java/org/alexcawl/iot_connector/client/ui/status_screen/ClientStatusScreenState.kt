package org.alexcawl.iot_connector.client.ui.status_screen

sealed interface ClientStatusScreenState {
    data object Initial : ClientStatusScreenState

    data class Status(
        val clientNetworkState: Result<Boolean>,
        val lastUpdate: Long
    ) : ClientStatusScreenState
}