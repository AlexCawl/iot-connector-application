package org.alexcawl.iot_connector.viewer.ui.screen

import androidx.compose.runtime.Immutable
import org.alexcawl.iot_connector.ui.state.data.ViewerDataRepresentationModel
import java.util.UUID

sealed interface ViewerScreenState {
    @Immutable
    data object Initial : ViewerScreenState

    @Immutable
    data class Viewer(
        val id: UUID,
        val endpoint: String,
        val name: String?,
        val representations: List<ViewerDataRepresentationModel>
    ) : ViewerScreenState

    @Immutable
    data class Failure(val cause: Throwable) : ViewerScreenState
}