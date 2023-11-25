package org.alexcawl.iot_connector.profile.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import org.alexcawl.iot_connector.profile.DaggerProfileComponent
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependenciesStore
import org.alexcawl.iot_connector.profile.ui.screen.add.component.installAddProfileScreen
import org.alexcawl.iot_connector.profile.ui.screen.edit.component.installEditProfileScreen
import org.alexcawl.iot_connector.profile.ui.screen.show.component.installShowProfilesScreen

sealed interface ProfileNavLocator {
    val route: String
    val arguments: List<NamedNavArgument>?

    data object ProfileShowScreen : ProfileNavLocator {
        override val route: String = "profiles"
        override val arguments: List<NamedNavArgument>? = null
    }

    data object ProfileEditScreen : ProfileNavLocator {
        override val route: String = "profile/{id}"
        override val arguments: List<NamedNavArgument> =
            listOf(navArgument("id") { type = NavType.StringType })
    }

    data object ProfileAddScreen : ProfileNavLocator {
        override val route: String = "profile"
        override val arguments: List<NamedNavArgument>? = null
    }
}

fun NavGraphBuilder.installProfileNavigation(navController: NavController) {
    val factory = DaggerProfileComponent.builder()
        .dependencies(ProfileDependenciesStore.dependencies).build()
            .provideFactory()

    installShowProfilesScreen(
        screenRoute = ProfileNavLocator.ProfileShowScreen.route,
        onNavigateToAddProfile = { navController.navigate(ProfileNavLocator.ProfileAddScreen.route) },
        onNavigateToEditProfile = { navController.navigate("${ProfileNavLocator.ProfileEditScreen.route}/${it}") },
        factory = factory
    )
    installAddProfileScreen(
        screenRoute = ProfileNavLocator.ProfileAddScreen.route, factory = factory
    )
    installEditProfileScreen(
        screenRoute = ProfileNavLocator.ProfileEditScreen.route,
        arguments = ProfileNavLocator.ProfileEditScreen.arguments,
        navController = navController,
        factory = factory
    )
}