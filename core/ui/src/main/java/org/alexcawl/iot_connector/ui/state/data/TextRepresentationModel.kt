package org.alexcawl.iot_connector.ui.state.data

import androidx.compose.runtime.Immutable

@Immutable
data class TextRepresentationModel(val text: String) : ViewerDataRepresentationModel {
    override val priority: Int
        get() = Int.MIN_VALUE
}