package org.alexcawl.iot_connector.profile.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.alexcawl.iot_connector.profile.ui.show_screen.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.show_screen.component.ShowProfilesScreen
import org.alexcawl.iot_connector.profile.ui.update_screen.add_screen.AddProfileViewModel
import org.alexcawl.iot_connector.profile.ui.update_screen.add_screen.component.AddProfileScreen
import org.alexcawl.iot_connector.profile.ui.update_screen.edit_screen.EditProfileViewModel
import org.alexcawl.iot_connector.profile.ui.update_screen.edit_screen.component.EditProfileScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

inline fun NavGraphBuilder.includeShowProfilesScreen(
    route: String,
    crossinline viewModelFactoryProducer: () -> ViewModelProvider.Factory,
    noinline onAddProfileAction: () -> Unit,
    noinline onEditProfileAction: (UUID) -> Unit
) = composable(route = route) {
    val viewModel = composeViewModel(
        modelClass = ShowProfilesViewModel::class.java,
        viewModelInstanceCreator = viewModelFactoryProducer
    )
    val state by viewModel.state.collectAsState()

    ShowProfilesScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateToAddProfile = onAddProfileAction,
        onNavigateToEditProfile = onEditProfileAction
    )
}

inline fun NavGraphBuilder.includeAddProfileScreen(
    route: String,
    crossinline viewModelFactoryProducer: () -> ViewModelProvider.Factory,
    noinline onNavigateBack: () -> Unit
) = composable(route) {
    val viewModel = composeViewModel(
        modelClass = AddProfileViewModel::class.java,
        viewModelInstanceCreator = viewModelFactoryProducer
    )
    val state by viewModel.state.collectAsState()

    AddProfileScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}

const val EDIT_PROFILE_ID: String = "id"

inline fun NavGraphBuilder.includeEditProfileScreen(
    route: String,
    crossinline viewModelFactoryProducer: () -> ViewModelProvider.Factory,
    noinline onNavigateBack: () -> Unit,
    noinline onNavigateWithException: (Throwable) -> Unit
) = composable(
    route = route,
    arguments = listOf(navArgument(name = EDIT_PROFILE_ID) { type = NavType.StringType })
) { backStack ->
    val profileId: Result<UUID> = runCatching {
        UUID.fromString(backStack.arguments?.getString(EDIT_PROFILE_ID))
    }
    val viewModel = composeViewModel(
        modelClass = EditProfileViewModel::class.java,
        viewModelInstanceCreator = viewModelFactoryProducer
    )
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = profileId) {
        when (val id = profileId.getOrNull()) {
            null -> onNavigateWithException(profileId.exceptionOrNull()!!)
            else -> viewModel.setProfileId(id)
        }
    }
    EditProfileScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}
