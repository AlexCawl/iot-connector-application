package org.alexcawl.iot_connector.profile.ui

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.profile.DaggerProfileComponent
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependenciesStore
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.screen.show.component.ShowProfilesScreen
import org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.update.add.component.AddProfileScreen
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.component.EditProfileScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

fun NavGraphBuilder.profileNavigation(navController: NavController) {
    val factory = DaggerProfileComponent.builder()
        .dependencies(ProfileDependenciesStore.dependencies).build()
        .provideFactory()

    installShowProfilesScreen(
        screenRoute = ProfileNavLocator.ProfileShowScreen.route,
        onNavigateToAddProfile = { navController.navigate(ProfileNavLocator.ProfileAddScreen.route) },
        onNavigateToEditProfile = { navController.navigate(ProfileNavLocator.ProfileEditScreen.buildRoute(it)) },
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
        val viewModel = composeViewModel(modelClass = ShowProfilesViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        ShowProfilesScreen(
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
        val viewModel = composeViewModel(modelClass = AddProfileViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()

        AddProfileScreen(
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
        val viewModel = composeViewModel(modelClass = EditProfileViewModel::class.java) { factory }
        val state by viewModel.state.collectAsState()
        LaunchedEffect(key1 = null) { viewModel.setProfileId(profileId) }
        EditProfileScreen(
            state = state,
            onAction = viewModel::handle,
            onNavigateBack = { navController.navigateUp() }
        )
    }
}