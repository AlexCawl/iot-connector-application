package org.alexcawl.iot_connector.client.ui.status_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.alexcawl.iot_connector.client.ui.R
import org.alexcawl.iot_connector.client.ui.status_screen.ClientStatusScreenAction
import org.alexcawl.iot_connector.client.ui.status_screen.ClientStatusScreenState
import org.alexcawl.iot_connector.ui.components.card.InfoCard
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import org.alexcawl.iot_connector.ui.util.toDateFormat

@Composable
fun ClientStatusScreen(
    state: ClientStatusScreenState,
    onAction: (ClientStatusScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    floatingActionButton = {
        FloatingActionButton(onClick = { onAction(ClientStatusScreenAction.Refresh) }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
        }
    }
) { paddingValues: PaddingValues ->
    val paddingModifier = Modifier.padding(paddingValues)
    when (state) {
        is ClientStatusScreenState.Initial -> LoadingScreen(
            modifier = paddingModifier.fillMaxSize(),
            title = {
                Text(
                    text = stringResource(id = R.string.loading_client),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )

        is ClientStatusScreenState.Status -> Column(
            modifier = paddingModifier.padding(ExtendedTheme.padding.medium),
            verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            NetworkStatusCard(isNetworkAvailable = state.clientNetworkState)
            InfoCard(
                key = "Last update",
                value = state.lastUpdate.toDateFormat(),
                statusIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Composable
@ThemedPreview
private fun LoadingPreview() {
    val state = ClientStatusScreenState.Initial
    IoTConnectorTheme {
        ClientStatusScreen(
            state = state,
            onAction = {}
        )
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    val state = ClientStatusScreenState.Status(
        Result.failure(IllegalStateException()),
        System.currentTimeMillis()
    )
    IoTConnectorTheme {
        ClientStatusScreen(
            state = state,
            onAction = {}
        )
    }
}