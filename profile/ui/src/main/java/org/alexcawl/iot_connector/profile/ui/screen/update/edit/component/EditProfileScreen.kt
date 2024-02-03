package org.alexcawl.iot_connector.profile.ui.screen.update.edit.component

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
import org.alexcawl.iot_connector.profile.ui.R
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreen
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.Delete
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.NotFound
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun EditProfileScreen(
    state: ProfileScreenState,
    onAction: (ProfileScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) = when (state) {
    is NotFound -> LaunchedEffect(key1 = null) { onNavigateBack() }
    else -> ProfileScreen(
        state = state,
        onAction = onAction,
        onNavigateBack = onNavigateBack,
        title = {
            Text(
                text = stringResource(id = R.string.edit_profile_title),
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

@ThemedPreview
@Composable
private fun Preview() {
    IoTConnectorTheme {
        EditProfileScreen(
            state = ProfileScreenState.Builder(),
            onAction = {},
            onNavigateBack = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}