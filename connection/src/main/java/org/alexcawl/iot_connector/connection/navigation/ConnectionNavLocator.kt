package org.alexcawl.iot_connector.connection.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.util.UUID

sealed interface ConnectionNavLocator {
    val route: String
    val arguments: List<NamedNavArgument>?

    data object ConnectionsShowScreen : ConnectionNavLocator {
        override val route: String = "connections"
        override val arguments: List<NamedNavArgument>? = null
    }

    data object ConnectionEditScreen : ConnectionNavLocator {
        override val route: String = "edit_connection/{id}"
        override val arguments: List<NamedNavArgument> =
            listOf(navArgument("id") { type = NavType.StringType })

        fun buildRoute(id: UUID): String = "edit_connection/${id}"
    }

    data object ConnectionAddScreen : ConnectionNavLocator {
        override val route: String = "add_connection"
        override val arguments: List<NamedNavArgument>? = null
    }
}