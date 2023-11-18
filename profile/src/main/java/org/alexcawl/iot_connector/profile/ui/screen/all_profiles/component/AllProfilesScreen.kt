package org.alexcawl.iot_connector.profile.ui.screen.all_profiles.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.AllProfilesScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.AllProfilesScreenState

@Composable
fun AllProfilesScreen(
    state: AllProfilesScreenState,
    onAction: (AllProfilesScreenAction) -> Unit
) {
    Text(text = state.toString())
}