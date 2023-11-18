package org.alexcawl.iot_connector.profile.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.alexcawl.iot_connector.profile.DaggerProfileComponent
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependenciesStore
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.AllProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.component.AllProfilesScreen
import org.alexcawl.iot_connector.profile.ui.screen.edit_profile.EditProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.edit_profile.component.EditProfileScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel

fun NavGraphBuilder.profileGraph(navController: NavController) {
    navigation(startDestination = "profiles", route = "profile") {
        composable(route = "profiles") {
            val viewModel = composeViewModel(modelClass = AllProfilesViewModel::class.java) {
                DaggerProfileComponent.builder()
                    .dependencies(ProfileDependenciesStore.dependencies)
                    .build()
                    .provideFactory()
            }
            val state by viewModel.state.collectAsState()
            AllProfilesScreen(state = state, onAction = { viewModel.handle(it) })
        }
        composable(
            route = "profile/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val profileId: String = backStack.arguments?.getString("id").let {
                when (it) {
                    null -> navController.navigateUp().let { "" }
                    else -> it
                }
            }
            val viewModel = composeViewModel(modelClass = EditProfileViewModel::class.java) {
                DaggerProfileComponent.builder()
                    .dependencies(ProfileDependenciesStore.dependencies)
                    .build()
                    .provideFactory()
            }
            val state by viewModel.state.collectAsState()
            EditProfileScreen(state = state, onAction = { viewModel.handle(it) })
        }
    }

}