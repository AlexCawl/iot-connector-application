package org.alexcawl.iot_connector.profile.ui.screen.edit.component

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.profile.ui.screen.edit.EditProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.edit.EditProfileViewModel
import org.alexcawl.iot_connector.ui.util.composeViewModel
import java.util.UUID

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
        viewModel.handle(EditProfileScreenAction.InstallProfileById(profileId))
        EditProfileScreen(state = state, onAction = { viewModel.handle(it) })
    }
}