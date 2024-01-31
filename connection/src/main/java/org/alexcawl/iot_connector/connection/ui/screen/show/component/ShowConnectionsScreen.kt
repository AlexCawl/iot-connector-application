package org.alexcawl.iot_connector.connection.ui.screen.show.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.connection.R
import org.alexcawl.iot_connector.connection.ui.screen.show.ShowConnectionsScreenAction
import org.alexcawl.iot_connector.connection.ui.screen.show.ShowConnectionsScreenState
import org.alexcawl.iot_connector.ui.components.placeholder.EmptyScreen
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.state.ConnectionState
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import java.util.UUID

@Suppress("UNUSED_PARAMETER")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ShowConnectionsScreen(
    state: ShowConnectionsScreenState,
    onAction: (ShowConnectionsScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAddConnection: () -> Unit,
    onNavigateToEditConnection: (UUID) -> Unit,
    onNavigateToViewConnection: (UUID) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            title = {
                Text(
                    text = "Connections",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )
    },
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
            0 -> Column(
                modifier = paddingModifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NetworkStatusCard(
                    isNetworkAvailable = state.networkAvailability,
                    modifier = Modifier.padding(ExtendedTheme.padding.medium)
                )
                EmptyScreen(
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
            }

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
                item(key = NETWORK_AVAILABILITY_LABEL) {
                    NetworkStatusCard(isNetworkAvailable = state.networkAvailability)
                }
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

private const val NETWORK_AVAILABILITY_LABEL: String = "NETWORK_AVAILABILITY_KEY"

@Composable
@ThemedPreview
private fun LoadingPreview() {
    val state = ShowConnectionsScreenState.Initial
    IoTConnectorTheme {
        ShowConnectionsScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
            onNavigateToAddConnection = {},
            onNavigateToEditConnection = {},
            onNavigateToViewConnection = {}
        )
    }
}

@Composable
@ThemedPreview
private fun EmptyPreview() {
    val state = ShowConnectionsScreenState.Viewer(
        listOf(),
        Result.failure(IllegalStateException())
    )
    IoTConnectorTheme {
        ShowConnectionsScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
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
        ),
        Result.failure(IllegalStateException())
    )
    IoTConnectorTheme {
        ShowConnectionsScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
            onNavigateToAddConnection = {},
            onNavigateToEditConnection = {},
            onNavigateToViewConnection = {}
        )
    }
}