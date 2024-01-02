package org.alexcawl.iot_connector.profile.ui.screen.add.component

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileViewModel
import org.alexcawl.iot_connector.ui.util.composeViewModel

fun NavGraphBuilder.installAddProfileScreen(
    screenRoute: String,
    onNavigateBack: () -> Unit,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        val viewModel = composeViewModel(modelClass = AddProfileViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        AddProfileScreen(state = state, onAction = viewModel::handle, onNavigateBack = onNavigateBack)
    }
}