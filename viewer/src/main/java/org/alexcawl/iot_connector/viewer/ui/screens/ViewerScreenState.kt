package org.alexcawl.iot_connector.viewer.ui.screens

import org.alexcawl.iot_connector.ui.state.ViewerState

sealed interface ViewerScreenState {
    data object Initial : ViewerScreenState

    data class Viewer(val state: ViewerState) : ViewerScreenState

    data class Failure(val cause: Throwable) : ViewerScreenState
}