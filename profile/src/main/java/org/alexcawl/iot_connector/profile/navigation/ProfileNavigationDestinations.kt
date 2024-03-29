package org.alexcawl.iot_connector.profile.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.alexcawl.iot_connector.profile.ProfileComponentStore
import org.alexcawl.iot_connector.profile.ui.show_screen.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.show_screen.component.ShowProfilesScreen
import org.alexcawl.iot_connector.profile.ui.update_screen.add_screen.AddProfileViewModel
import org.alexcawl.iot_connector.profile.ui.update_screen.add_screen.component.AddProfileScreen
import org.alexcawl.iot_connector.profile.ui.update_screen.edit_screen.EditProfileViewModel
import org.alexcawl.iot_connector.profile.ui.update_screen.edit_screen.component.EditProfileScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

fun NavGraphBuilder.includeShowProfilesScreen(
    route: String,
    onAddProfileAction: () -> Unit,
    onEditProfileAction: (UUID) -> Unit
) = composable(
    route = route,
    enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
    exitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
) {
    val viewModel = composeViewModel(
        modelClass = ShowProfilesViewModel::class.java,
        viewModelInstanceCreator = { ProfileComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    ShowProfilesScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateToAddProfile = onAddProfileAction,
        onNavigateToEditProfile = onEditProfileAction
    )
}

fun NavGraphBuilder.includeAddProfileScreen(
    route: String,
    onNavigateBack: () -> Unit
) = composable(
    route=route,
    enterTransition = { slideInVertically(initialOffsetY = { it }) },
    exitTransition = { slideOutVertically(targetOffsetY = { it }) }
) {
    val viewModel = composeViewModel(
        modelClass = AddProfileViewModel::class.java,
        viewModelInstanceCreator = { ProfileComponentStore.component.provideFactory() }
    )
    val state by viewModel.state.collectAsState()
    AddProfileScreen(
        state = state,
        onAction = viewModel::handle,
        onNavigateBack = onNavigateBack
    )
}

const val EDIT_PROFILE_ID: String = "id"

fun NavGraphBuilder.includeEditProfileScreen(
    route: String,
    onNavigateBack: () -> Unit,
    onNavigateWithException: (Throwable) -> Unit
) = composable(
    route = route,
    arguments = listOf(navArgument(name = EDIT_PROFILE_ID) { type = NavType.StringType }),
    enterTransition = { slideInVertically(initialOffsetY = { it }) },
    exitTransition = { slideOutVertically(targetOffsetY = { it }) }
) { backStack ->
    val profileId: Result<UUID> = runCatching {
        UUID.fromString(backStack.arguments?.getString(EDIT_PROFILE_ID))
    }
    val viewModel = composeViewModel(
        modelClass = EditProfileViewModel::class.java,
        viewModelInstanceCreator = { ProfileComponentStore.component.provideFactory() }
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
