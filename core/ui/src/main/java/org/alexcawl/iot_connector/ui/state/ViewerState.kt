package org.alexcawl.iot_connector.ui.state

import androidx.compose.runtime.Immutable
import org.alexcawl.iot_connector.ui.state.data.ViewerData
import java.util.UUID

@Immutable
data class ViewerState(
    val id: UUID,
    val endpoint: String,
    val name: String?,
    val viewsData: List<ViewerData>
)