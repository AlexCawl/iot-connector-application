package org.alexcawl.iot_connector.profile.ui.screen.edit_profile.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.alexcawl.iot_connector.profile.ui.screen.edit_profile.EditProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.edit_profile.EditProfileScreenState

@Composable
fun EditProfileScreen(
    state: EditProfileScreenState,
    onAction: (EditProfileScreenAction) -> Unit
) {
    Text(text = state.toString())
}