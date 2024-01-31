package org.alexcawl.iot_connector.ui.components.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.alexcawl.iot_connector.ui.util.loremIpsum
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier, title: @Composable () -> Unit = {}
) = Box(
    modifier = modifier.background(color = Color.Transparent), contentAlignment = Alignment.Center
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.small, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(ExtendedTheme.iconSize.big),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        title()
    }
}

@ThemedPreview
@Composable
private fun PreviewWithoutTitle() {
    IoTConnectorTheme {
        LoadingScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        )
    }
}

@ThemedPreview
@Composable
private fun PreviewWithTitle() {
    IoTConnectorTheme {
        LoadingScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
        ) {
            Text(
                text = loremIpsum(3),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}