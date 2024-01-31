package org.alexcawl.iot_connector.viewer.ui.screens.params

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.alexcawl.iot_connector.ui.state.data.ParametersRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun ParametersViewerScreen(
    state: ParametersRepresentationModel,
    modifier: Modifier = Modifier
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
    horizontalAlignment = Alignment.Start,
    contentPadding = PaddingValues(ExtendedTheme.padding.medium)
) {
    val params = state.parameters.toList()
    items(params, key = Pair<String, String>::first) {
        ParameterCard(key = it.first, value = it.second)
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    val state = ParametersRepresentationModel(
        mapOf(
            "key1" to "value1",
            "key2" to "value2",
            "key3" to "value3",
            "key4" to "value4",
            "key5" to "value5"
        )
    )
    IoTConnectorTheme {
        ParametersViewerScreen(state = state)
    }
}