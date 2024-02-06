package org.alexcawl.iot_connector.viewer.ui.screen.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.iot_connector.ui.util.loremIpsum
import org.alexcawl.iot_connector.ui.state.data.TextRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun TextViewerScreen(
    state: TextRepresentationModel,
    modifier: Modifier = Modifier
) = Scaffold {
    SelectionContainer(
        modifier = Modifier.padding(it)
    ) {
        Text(
            text = state.text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .verticalScroll(state = rememberScrollState())
                .padding(ExtendedTheme.padding.medium)
        )
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    IoTConnectorTheme {
        TextViewerScreen(
            state = TextRepresentationModel(loremIpsum(100)),
            modifier = Modifier.fillMaxSize()
        )
    }
}