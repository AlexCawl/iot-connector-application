package org.alexcawl.iot_connector.viewer.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.ui.util.composeViewModel
import org.alexcawl.iot_connector.viewer.ViewerComponentStore
import org.alexcawl.iot_connector.viewer.ui.screen.ViewerScreen
import org.alexcawl.iot_connector.viewer.ui.screen.ViewerScreenViewModel
import java.util.UUID

fun NavGraphBuilder.viewerNavigation(navController: NavController) {
    val factory = ViewerComponentStore.component.provideFactory()

    composable(ViewerNavLocator.route) { backStack ->
        val rawConnectionId: String? = backStack.arguments?.getString("id")
        lateinit var connectionId: UUID
        try {
            connectionId = UUID.fromString(rawConnectionId)
        } catch (exception: Exception) {
            navController.navigateUp()
        }
        val viewModel = composeViewModel(modelClass = ViewerScreenViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()
        LaunchedEffect(key1 = null) { viewModel.setConnectionId(connectionId) }
        ViewerScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateBack = { navController.navigateUp() }
        )
    }
}