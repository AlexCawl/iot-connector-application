package org.alexcawl.iot_connector.profile.ui.screen.show.component

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel
import org.alexcawl.iot_connector.ui.util.composeViewModel

internal fun NavGraphBuilder.installShowProfilesScreen(
    route: String,
    onAddProfileAction: () -> Unit,
    factory: ViewModelProvider.Factory
) {
    composable(route) {
        val viewModel = composeViewModel(modelClass = ShowProfilesViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()
        ShowProfilesScreen(
            state = state,
            onAction = viewModel::handle,
            onAddProfileAction = onAddProfileAction
        )
    }
}