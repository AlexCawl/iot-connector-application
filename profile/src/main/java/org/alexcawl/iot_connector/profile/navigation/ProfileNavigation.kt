package org.alexcawl.iot_connector.profile.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.profile.di.ProfileComponentStore
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

fun NavGraphBuilder.profileNavigation(navController: NavController) {
    val factory = ProfileComponentStore.component.provideFactory()

    installShowProfilesScreen(
        screenRoute = ProfileNavLocator.ProfileShowScreen.route,
        onNavigateToAddProfile = { navController.navigate(ProfileNavLocator.ProfileAddScreen.route) },
        onNavigateToEditProfile = { navController.navigate(
            ProfileNavLocator.ProfileEditScreen.buildRoute(
                it
            )
        ) },
        factory = factory,
        onNavigateBack = { navController.navigateUp() }
    )
    installAddProfileScreen(
        screenRoute = ProfileNavLocator.ProfileAddScreen.route,
        factory = factory,
        navController = navController
    )
    installEditProfileScreen(
        screenRoute = ProfileNavLocator.ProfileEditScreen.route,
        arguments = ProfileNavLocator.ProfileEditScreen.arguments,
        navController = navController,
        factory = factory
    )
}

internal fun NavGraphBuilder.installShowProfilesScreen(
    screenRoute: String,
    onNavigateBack: () -> Unit,
    onNavigateToAddProfile: () -> Unit,
    onNavigateToEditProfile: (UUID) -> Unit,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        val viewModel = composeViewModel(modelClass = org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        org.alexcawl.iot_connector.profile.ui.screen.show.component.ShowProfilesScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateToAddProfile = onNavigateToAddProfile,
            onNavigateToEditProfile = onNavigateToEditProfile,
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavGraphBuilder.installAddProfileScreen(
    screenRoute: String,
    navController: NavController,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        val viewModel = composeViewModel(modelClass = org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        org.alexcawl.iot_connector.profile.ui.screen.update.add.component.AddProfileScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateBack = { navController.navigateUp() }
        )
    }
}

fun NavGraphBuilder.installEditProfileScreen(
    screenRoute: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute, arguments) { backStack ->
        val rawProfileId: String? = backStack.arguments?.getString("id")
        lateinit var profileId: UUID
        try {
            profileId = UUID.fromString(rawProfileId)
        } catch (exception: Exception) {
            navController.navigateUp()
        }
        val viewModel = composeViewModel(modelClass = org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()
        LaunchedEffect(key1 = null) { viewModel.setProfileId(profileId) }
        org.alexcawl.iot_connector.profile.ui.screen.update.edit.component.EditProfileScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateBack = { navController.navigateUp() }
        )
    }
}