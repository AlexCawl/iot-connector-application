package org.alexcawl.iot_connector.ui.state.data

import androidx.compose.runtime.Immutable

@Immutable
data class ParametersRepresentationModel(val parameters: Map<String, String>): ViewerDataRepresentationModel {
    override val priority: Int
        get() = 0
}