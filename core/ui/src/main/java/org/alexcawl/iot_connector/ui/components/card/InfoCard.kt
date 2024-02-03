package org.alexcawl.iot_connector.ui.components.card

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

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