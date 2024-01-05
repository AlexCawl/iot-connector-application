package org.alexcawl.iot_connector.profile.ui.screen.show.component

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

internal fun NavGraphBuilder.installShowProfilesScreen(
    screenRoute: String,
    onNavigateBack: () -> Unit,
    onNavigateToAddProfile: () -> Unit,
    onNavigateToEditProfile: (UUID) -> Unit,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        val viewModel = composeViewModel(modelClass = ShowProfilesViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        ShowProfileScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateToAddProfile = onNavigateToAddProfile,
            onNavigateToEditProfile = onNavigateToEditProfile,
            onNavigateBack = onNavigateBack
        )
    }
}