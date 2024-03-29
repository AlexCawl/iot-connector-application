package org.alexcawl.iot_connector.profile.ui.update_screen.add_screen.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.profile.ui.R
import org.alexcawl.iot_connector.profile.ui.update_screen.ProfileScreen
import org.alexcawl.iot_connector.profile.ui.update_screen.ProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.update_screen.ProfileScreenState
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun AddProfileScreen(
    state: ProfileScreenState,
    onAction: (ProfileScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) = ProfileScreen(
    state = state,
    onAction = onAction,
    onNavigateBack = onNavigateBack,
    title = {
        Text(
            text = stringResource(id = R.string.add_profile_title),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge
        )
    },
    floatingActionButton = {},
    modifier = modifier
)

@ThemedPreview
@Composable
private fun Preview() {
    IoTConnectorTheme {
        AddProfileScreen(
            state = ProfileScreenState.Builder(),
            onAction = {},
            onNavigateBack = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}