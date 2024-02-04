package org.alexcawl.iot_connector.connection.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.alexcawl.iot_connector.connection.ConnectionComponentStore
import org.alexcawl.iot_connector.connection.ui.show_screen.ShowConnectionsViewModel
import org.alexcawl.iot_connector.connection.ui.show_screen.component.ShowConnectionsScreen
import org.alexcawl.iot_connector.connection.ui.update_screen.add_screen.AddConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.update_screen.add_screen.component.AddConnectionScreen
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.EditConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.component.EditConnectionScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID


fun NavGraphBuilder.includeShowConnectionsScreen(
    route: String,
    onAddConnectionAction: () -> Unit,
    onEditConnectionAction: (UUID) -> Unit,
    onViewConnectionAction: (UUID) -> Unit
) = composable(route = route) {
    val viewModel = composeViewModel(
        modelClass = ShowConnectionsViewModel::class.java,
        viewModelInstanceCreator = { ConnectionComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    ShowConnectionsScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateToAddConnection = onAddConnectionAction,
        onNavigateToEditConnection = onEditConnectionAction,
        onNavigateToViewConnection = onViewConnectionAction
    )
}

fun NavGraphBuilder.includeAddConnectionScreen(
    route: String,
    onNavigateBack: () -> Unit
) = composable(route = route) {
    val viewModel = composeViewModel(
        modelClass = AddConnectionViewModel::class.java,
        viewModelInstanceCreator = { ConnectionComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    AddConnectionScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}

const val EDIT_CONNECTION_ID: String = "id"

fun NavGraphBuilder.includeEditConnectionScreen(
    route: String,
    onNavigateBack: () -> Unit,
    onNavigateWithException: (Throwable) -> Unit
) = composable(
    route = route,
    arguments = listOf(navArgument(name = EDIT_CONNECTION_ID) { type = NavType.StringType })
) { backStack ->
    val connectionId: Result<UUID> = runCatching {
        UUID.fromString(backStack.arguments?.getString(EDIT_CONNECTION_ID))
    }
    val viewModel = composeViewModel(
        modelClass = EditConnectionViewModel::class.java,
        viewModelInstanceCreator = { ConnectionComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = connectionId) {
        when (val id = connectionId.getOrNull()) {
            null -> onNavigateWithException(connectionId.exceptionOrNull()!!)
            else -> viewModel.setConnectionId(id)
        }
    }
    EditConnectionScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}