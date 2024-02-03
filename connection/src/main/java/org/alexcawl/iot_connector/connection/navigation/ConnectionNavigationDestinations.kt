package org.alexcawl.iot_connector.connection.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.alexcawl.iot_connector.connection.ui.show_screen.ShowConnectionsViewModel
import org.alexcawl.iot_connector.connection.ui.show_screen.component.ShowConnectionsScreen
import org.alexcawl.iot_connector.connection.ui.update_screen.add_screen.AddConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.update_screen.add_screen.component.AddConnectionScreen
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.EditConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.component.EditConnectionScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID


inline fun NavGraphBuilder.includeShowConnectionsScreen(
    route: String,
    crossinline viewModelFactoryProducer: () -> ViewModelProvider.Factory,
    noinline onNavigateBack: () -> Unit,
    noinline onAddConnectionAction: () -> Unit,
    noinline onEditConnectionAction: (UUID) -> Unit,
    noinline onViewConnectionAction: (UUID) -> Unit
) = composable(route = route) {
    val viewModel = composeViewModel(
        modelClass = ShowConnectionsViewModel::class.java,
        viewModelInstanceCreator = viewModelFactoryProducer
    )
    val state by viewModel.state.collectAsState()
    ShowConnectionsScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateToAddConnection = onAddConnectionAction,
        onNavigateToEditConnection = onEditConnectionAction,
        onNavigateToViewConnection = onViewConnectionAction,
        onNavigateBack = onNavigateBack
    )
}

inline fun NavGraphBuilder.includeAddConnectionScreen(
    route: String,
    crossinline viewModelFactoryProducer: () -> ViewModelProvider.Factory,
    noinline onNavigateBack: () -> Unit
) = composable(route = route) {
    val viewModel = composeViewModel(
        modelClass = AddConnectionViewModel::class.java,
        viewModelInstanceCreator = viewModelFactoryProducer
    )
    val state by viewModel.state.collectAsState()

    AddConnectionScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}

const val EDIT_CONNECTION_ID: String = "id"

inline fun NavGraphBuilder.installEditConnectionScreen(
    route: String,
    crossinline viewModelFactoryProducer: () -> ViewModelProvider.Factory,
    noinline onNavigateBack: () -> Unit,
    noinline onNavigateWithException: (Throwable) -> Unit
) {
    composable(route = route, arguments= listOf(navArgument(name = EDIT_CONNECTION_ID) { type = NavType.StringType })) { backStack ->
        val connectionId: Result<UUID> = runCatching {
            UUID.fromString(backStack.arguments?.getString(EDIT_CONNECTION_ID))
        }
        val viewModel = composeViewModel(
            modelClass = EditConnectionViewModel::class.java,
            viewModelInstanceCreator = viewModelFactoryProducer
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
}