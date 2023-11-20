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
import org.alexcawl.iot_connector.profile.ui.screen.show.component.installShowProfilesScreen
import org.alexcawl.iot_connector.profile.ui.screen.edit.EditProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.edit.EditProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.edit.component.EditProfileScreen
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

fun NavGraphBuilder.installProfileNavigation(navController: NavController) {
    val factory = DaggerProfileComponent.builder()
        .dependencies(ProfileDependenciesStore.dependencies)
        .build()
        .provideFactory()

    navigation(startDestination = "profiles", route = "profile") {
        installShowProfilesScreen("profiles", { }, factory)
        composable(
            route = "profile/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val rawProfileId: String? = backStack.arguments?.getString("id")
            lateinit var profileId: UUID
            try {
                profileId = UUID.fromString(rawProfileId)
            } catch (exception: Exception) {
                navController.navigateUp()
            }
            val viewModel = composeViewModel(modelClass = EditProfileViewModel::class.java) {
                DaggerProfileComponent.builder().dependencies(ProfileDependenciesStore.dependencies)
                    .build().provideFactory()
            }
            val state by viewModel.state.collectAsState()
            viewModel.handle(EditProfileScreenAction.InstallProfileById(profileId))
            EditProfileScreen(state = state, onAction = { viewModel.handle(it) })
        }
    }
}