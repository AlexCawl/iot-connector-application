package org.alexcawl.iot_connector.viewer.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.alexcawl.iot_connector.ui.util.composeViewModel
import org.alexcawl.iot_connector.viewer.ViewerComponentStore
import org.alexcawl.iot_connector.viewer.ui.screen.ViewerScreen
import org.alexcawl.iot_connector.viewer.ui.screen.ViewerScreenViewModel
import java.util.UUID

const val VIEW_CONNECTION_ID: String = "id"

fun NavGraphBuilder.includeViewerScreen(
    route: String,
    onNavigateBack: () -> Unit,
    onNavigateWithException: (Throwable) -> Unit
) = composable(
    route = route,
    arguments = listOf(navArgument(name = VIEW_CONNECTION_ID) { type = NavType.StringType })
) { backStack ->
    val connectionId: Result<UUID> = runCatching {
        UUID.fromString(backStack.arguments?.getString(VIEW_CONNECTION_ID))
    }
    val viewModel = composeViewModel(
        modelClass = ViewerScreenViewModel::class.java,
        viewModelInstanceCreator = { ViewerComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = connectionId) {
        when (val id = connectionId.getOrNull()) {
            null -> onNavigateWithException(connectionId.exceptionOrNull()!!)
            else -> viewModel.setConnectionId(id)
        }
    }
    ViewerScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}