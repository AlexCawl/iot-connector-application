package org.alexcawl.iot_connector.connection.ui

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.common.DEBUG_LOG_TAG
import org.alexcawl.iot_connector.connection.DaggerConnectionsComponent
import org.alexcawl.iot_connector.connection.dependencies.ConnectionDependenciesStore
import org.alexcawl.iot_connector.connection.ui.screen.show.ShowConnectionsViewModel
import org.alexcawl.iot_connector.connection.ui.screen.show.component.ShowConnectionsScreen
import org.alexcawl.iot_connector.connection.ui.screen.update.add.AddConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.screen.update.add.component.AddConnectionScreen
import org.alexcawl.iot_connector.connection.ui.screen.update.edit.EditConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.screen.update.edit.component.EditConnectionScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

fun NavGraphBuilder.connectionNavigation(
    navController: NavController,
    onNavigateToViewConnection: (UUID) -> Unit
) {
    val factory = DaggerConnectionsComponent.builder()
        .dependencies(ConnectionDependenciesStore.dependencies).build()
        .provideFactory()
    Log.d(DEBUG_LOG_TAG, "component init")

    installShowConnectionsScreen(
        screenRoute = ConnectionNavLocator.ConnectionsShowScreen.route,
        onNavigateToAddConnection = { navController.navigate(ConnectionNavLocator.ConnectionAddScreen.route) },
        onNavigateToEditConnection = { navController.navigate(ConnectionNavLocator.ConnectionEditScreen.buildRoute(it)) },
        onNavigateToViewConnection = onNavigateToViewConnection,
        onNavigateBack = { navController.navigateUp() },
        factory = factory
    )

    installAddConnectionScreen(
        screenRoute = ConnectionNavLocator.ConnectionAddScreen.route,
        factory = factory,
        navController = navController
    )

    installEditConnectionScreen(
        screenRoute = ConnectionNavLocator.ConnectionEditScreen.route,
        arguments = ConnectionNavLocator.ConnectionEditScreen.arguments,
        navController = navController,
        factory = factory
    )
}

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
            onNavigateToEditConnection = onNavigateToEditConnection,
            onNavigateToViewConnection = onNavigateToViewConnection,
            onNavigateBack = onNavigateBack
        )
    }
}

internal fun NavGraphBuilder.installAddConnectionScreen(
    screenRoute: String,
    navController: NavController,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        val viewModel = composeViewModel(modelClass = AddConnectionViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        AddConnectionScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateBack = { navController.navigateUp() }
        )
    }
}

internal fun NavGraphBuilder.installEditConnectionScreen(
    screenRoute: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute, arguments) { backStack ->
        val rawProfileId: String? = backStack.arguments?.getString("id")
        lateinit var connectionId: UUID
        try {
            connectionId = UUID.fromString(rawProfileId)
        } catch (exception: Exception) {
            navController.navigateUp()
        }
        val viewModel = composeViewModel(modelClass = EditConnectionViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()
        LaunchedEffect(key1 = null) { viewModel.setConnectionId(connectionId) }
        EditConnectionScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateBack = { navController.navigateUp() }
        )
    }
}