package org.alexcawl.iot_connector.connection.ui.screen.show.component

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.connection.ui.screen.show.ShowConnectionsViewModel
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

internal fun NavGraphBuilder.installShowConnectionsScreen(
    screenRoute: String,
    onNavigateBack: () -> Unit,
    onNavigateToAddConnection: () -> Unit,
    onNavigateToEditConnection: (UUID) -> Unit,
    onNavigateToViewConnection: (UUID) -> Unit,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        val viewModel = composeViewModel(modelClass = ShowConnectionsViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        ShowConnectionsScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateToAddConnection = onNavigateToAddConnection,
            onNavigateToEditConnection = onNavigateToViewConnection,
            onNavigateToViewConnection = onNavigateToEditConnection,
            onNavigateBack = onNavigateBack
        )
    }
}