package org.alexcawl.iot_connector.ui.components.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
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
fun EmptyScreen(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    subtitle: @Composable () -> Unit
) = Box(
    modifier = modifier, contentAlignment = Alignment.Center
) {
    Column(
        modifier = Modifier.background(color = Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.large, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()
        Column(
            verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.small, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            title()
            subtitle()
        }
    }
}

@ThemedPreview
@Composable
private fun Preview() {
    IoTConnectorTheme {
        EmptyScreen(
            title = {
                Text(
                    text = loremIpsum(3),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            subtitle = {
                Text(
                    text = loremIpsum(5),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    modifier = Modifier.size(ExtendedTheme.iconSize.large),
                    tint = MaterialTheme.colorScheme.secondary
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        )
    }
}