package org.alexcawl.iot_connector.connection.ui.show_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.alexcawl.iot_connector.connection.ui.R
import org.alexcawl.iot_connector.connection.ui.show_screen.ShowConnectionsScreenAction
import org.alexcawl.iot_connector.connection.ui.show_screen.ShowConnectionsScreenState
import org.alexcawl.iot_connector.ui.components.placeholder.EmptyScreen
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.state.ConnectionState
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import java.util.UUID

@Suppress("UNUSED_PARAMETER")
@Composable
fun ShowConnectionsScreen(
    state: ShowConnectionsScreenState,
    onAction: (ShowConnectionsScreenAction) -> Unit,
    onNavigateToAddConnection: () -> Unit,
    onNavigateToEditConnection: (UUID) -> Unit,
    onNavigateToViewConnection: (UUID) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    floatingActionButton = {
        FloatingActionButton(onClick = onNavigateToAddConnection) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
) { paddingValues: PaddingValues ->
    val paddingModifier = Modifier.padding(paddingValues)
    when (state) {
        is ShowConnectionsScreenState.Initial -> LoadingScreen(
            modifier = paddingModifier.fillMaxSize(),
            title = {
                Text(
                    text = stringResource(id = R.string.loading_connections_placeholder),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )

        is ShowConnectionsScreenState.Viewer -> when (state.connections.size) {
            0 -> EmptyScreen(
                modifier = Modifier.fillMaxSize(),
                title = {
                    Text(
                        text = stringResource(id = R.string.no_connections_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                subtitle = {
                    Text(
                        text = stringResource(id = R.string.no_connections_subtitle),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
            else -> LazyColumn(
                modifier = paddingModifier,
                verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                contentPadding = PaddingValues(
                    start = ExtendedTheme.padding.medium,
                    top = ExtendedTheme.padding.medium,
                    end = ExtendedTheme.padding.medium,
                    bottom = ExtendedTheme.padding.large * 3
                )
            ) {
                items(state.connections, key = ConnectionState::id) {
                    ConnectionCard(
                        connection = it,
                        onClicked = onNavigateToViewConnection,
                        onEditClicked = onNavigateToEditConnection
                    )
                }
            }
        }
    }
}

@Composable
@ThemedPreview
private fun LoadingPreview() {
    val state = ShowConnectionsScreenState.Initial
    IoTConnectorTheme {
        ShowConnectionsScreen(
            state = state,
            onAction = {},
            onNavigateToAddConnection = {},
            onNavigateToEditConnection = {},
            onNavigateToViewConnection = {}
        )
    }
}

@Composable
@ThemedPreview
private fun EmptyPreview() {
    val state = ShowConnectionsScreenState.Viewer(listOf())
    IoTConnectorTheme {
        ShowConnectionsScreen(
            state = state,
            onAction = {},
            onNavigateToAddConnection = {},
            onNavigateToEditConnection = {},
            onNavigateToViewConnection = {}
        )
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    val state = ShowConnectionsScreenState.Viewer(
        listOf(
            ConnectionState(
                id = UUID.randomUUID(),
                endpoint = "/post/latest/1",
                name = "Latest Posts"
            ),
            ConnectionState(
                id = UUID.randomUUID(),
                endpoint = "/post/test",
            ),
        )
    )
    IoTConnectorTheme {
        ShowConnectionsScreen(
            state = state,
            onAction = {},
            onNavigateToAddConnection = {},
            onNavigateToEditConnection = {},
            onNavigateToViewConnection = {}
        )
    }
}