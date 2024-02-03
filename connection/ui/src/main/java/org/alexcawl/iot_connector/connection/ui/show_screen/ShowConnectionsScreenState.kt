package org.alexcawl.iot_connector.connection.ui.show_screen

import org.alexcawl.iot_connector.ui.state.ConnectionState

sealed interface ShowConnectionsScreenState {
    data object Initial : ShowConnectionsScreenState

    data class Viewer(
        val connections: List<ConnectionState>,
        val networkAvailability: Result<Boolean>
    ) : ShowConnectionsScreenState
}