package org.alexcawl.iot_connector.profile.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.util.UUID

sealed interface ProfileNavLocator {
    val route: String
    val arguments: List<NamedNavArgument>?

    data object ProfileShowScreen : ProfileNavLocator {
        override val route: String = "profiles"
        override val arguments: List<NamedNavArgument>? = null
    }

    data object ProfileEditScreen : ProfileNavLocator {
        override val route: String = "edit_profile/{id}"
        override val arguments: List<NamedNavArgument> =
            listOf(navArgument("id") { type = NavType.StringType })

        fun buildRoute(id: UUID): String = "edit_profile/${id}"
    }

    data object ProfileAddScreen : ProfileNavLocator {
        override val route: String = "add_profile"
        override val arguments: List<NamedNavArgument>? = null
    }
}