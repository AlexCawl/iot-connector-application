package org.alexcawl.iot_connector.connection.navigation

import org.alexcawl.iot_connector.ui.util.NavigationNode
import java.util.UUID

sealed interface ConnectionNavigationNode : NavigationNode {

    data object ConnectionsShowScreen : ConnectionNavigationNode {
        override val route: String = "connections"

        override fun buildRoute(vararg arguments: Any?): String = route
    }

    data object ConnectionEditScreen : ConnectionNavigationNode {
        override val route: String = "edit_connection/{id}"

        @Throws(IllegalArgumentException::class)
        override fun buildRoute(vararg arguments: Any?): String = try {
            val id = UUID.fromString(arguments[0].toString())
            "edit_connection/${id}"
        } catch (exception: RuntimeException) {
            throw IllegalArgumentException(exception)
        }
    }

    data object ConnectionAddScreen : ConnectionNavigationNode {
        override val route: String = "add_connection"

        override fun buildRoute(vararg arguments: Any?): String = route
    }
}