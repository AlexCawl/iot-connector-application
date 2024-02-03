package org.alexcawl.iot_connector.connection.ui.show.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.components.CardScaffold
import org.alexcawl.iot_connector.ui.state.ConnectionState
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import java.util.UUID

@Composable
fun ConnectionCard(
    connection: ConnectionState,
    onClicked: (UUID) -> Unit,
    onEditClicked: (UUID) -> Unit,
    modifier: Modifier = Modifier
) = CardScaffold(
    modifier = modifier,
    title = {
        Text(
            text = connection.name ?: connection.endpoint,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
    subtitle = {
        if (connection.name != null) {
            Text(
                text = connection.endpoint,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    },
    onClick = { onClicked(connection.id) },
    statusIcon = {},
    configurationIcon = {
        IconButton(
            onClick = { onEditClicked(connection.id) },
            modifier = Modifier.fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(ExtendedTheme.iconSize.small)
            )
        }
    },
    body = {}
)

@Composable
@ThemedPreview
private fun Preview() {
    val connectionWithName = ConnectionState(
        id = UUID.randomUUID(),
        endpoint = "/post/latest/1",
        name = "Latest Posts"
    )
    val connectionWithoutName = ConnectionState(
        id = UUID.randomUUID(),
        endpoint = "/post/test",
    )

    IoTConnectorTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top)
        ) {
            ConnectionCard(
                connection = connectionWithName,
                onClicked = {},
                onEditClicked = {}
            )
            ConnectionCard(
                connection = connectionWithoutName,
                onClicked = {},
                onEditClicked = {}
            )
        }
    }
}