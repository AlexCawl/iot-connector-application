package org.alexcawl.iot_connector.profile.navigation

import org.alexcawl.iot_connector.ui.util.NavigationNode
import java.util.UUID

sealed interface ProfileNavigationNode : NavigationNode {
    data object ProfileShowScreen : ProfileNavigationNode {
        override val route: String = "profiles"

        override fun buildRoute(vararg arguments: Any?): String = route
    }

    data object ProfileEditScreen : ProfileNavigationNode {
        override val route: String = "edit_profile/{id}"

        @Throws(IllegalArgumentException::class)
        override fun buildRoute(vararg arguments: Any?): String = try {
            val id = UUID.fromString(arguments[0].toString())
            "edit_profile/${id}"
        } catch (exception: RuntimeException) {
            throw IllegalArgumentException(exception)
        }
    }

    data object ProfileAddScreen : ProfileNavigationNode {
        override val route: String = "add_profile"
        override fun buildRoute(vararg arguments: Any?): String = route
    }
}