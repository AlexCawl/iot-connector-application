package org.alexcawl.iot_connector.client.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.client.ClientComponentStore
import org.alexcawl.iot_connector.client.ui.status_screen.ClientStatusScreenViewModel
import org.alexcawl.iot_connector.client.ui.status_screen.component.ClientStatusScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel

fun NavGraphBuilder.includeClientStatusScreen(
    route: String
) = composable(
    route = route,
    enterTransition = { fadeIn() },
    exitTransition = { fadeOut() }
) {
    val viewModel = composeViewModel(
        modelClass = ClientStatusScreenViewModel::class.java,
        viewModelInstanceCreator = { ClientComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    ClientStatusScreen(
        state = state,
        onAction = viewModel::handle
    )
}