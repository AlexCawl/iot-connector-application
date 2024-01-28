package org.alexcawl.iot_connector.viewer.ui.screens.params

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.components.IconSmall
import org.alexcawl.iot_connector.ui.components.ItemCard
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun ParameterCard(
    key: String,
    value: String,
    modifier: Modifier = Modifier
) = ItemCard(
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
    statusIcon = {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier.size(IconSmall)
        )
    },
    configurationIcon = {},
    body = {},
    modifier = modifier
)

@Composable
@ThemedPreview
private fun Preview() {
    IoTConnectorTheme {
        Column {
            ParameterCard(key = "key", value = "value")
        }
    }
}