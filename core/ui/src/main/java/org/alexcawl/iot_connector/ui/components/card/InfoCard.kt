package org.alexcawl.iot_connector.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun InfoCard(
    key: String,
    value: String,
    statusIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = CardScaffold(
    title = {
        Text(
            text = key,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
    subtitle = {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
    statusIcon = statusIcon,
    configurationIcon = {},
    body = {},
    modifier = modifier
)

@Composable
@ThemedPreview
private fun Preview() {
    IoTConnectorTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            InfoCard(
                key = "key1",
                value = "value1",
                statusIcon = {}
            )
            InfoCard(
                key = "key2",
                value = "value2",
                statusIcon = {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
                }
            )
        }
    }
}