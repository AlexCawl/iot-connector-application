package org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.connection.ui.R
import org.alexcawl.iot_connector.connection.ui.update_screen.UpdateConnectionScreen
import org.alexcawl.iot_connector.connection.ui.update_screen.UpdateConnectionScreenAction
import org.alexcawl.iot_connector.connection.ui.update_screen.UpdateConnectionScreenState
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.Delete
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.NotFound
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun EditConnectionScreen(
    state: UpdateConnectionScreenState,
    onAction: (UpdateConnectionScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) = when (state) {
    NotFound -> LaunchedEffect(key1 = null) { onNavigateBack() }
    else -> UpdateConnectionScreen(
        state = state,
        onAction = onAction,
        onNavigateBack = onNavigateBack,
        title = {
            Text(
                text = stringResource(id = R.string.edit_connection_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(Delete) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        },
        modifier = modifier
    )
}

@Composable
@ThemedPreview
private fun Preview() {
    IoTConnectorTheme {
        EditConnectionScreen(
            state = UpdateConnectionScreenState.Builder(),
            onAction = {},
            onNavigateBack = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}