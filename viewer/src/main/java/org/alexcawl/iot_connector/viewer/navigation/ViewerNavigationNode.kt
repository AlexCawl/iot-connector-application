package org.alexcawl.iot_connector.viewer.navigation

import org.alexcawl.iot_connector.ui.util.NavigationNode
import java.util.UUID

sealed interface ViewerNavigationNode : NavigationNode {
    data object MainViewer : ViewerNavigationNode {
        override val route: String = "/viewer/{id}"

        @Throws(IllegalArgumentException::class)
        override fun buildRoute(vararg arguments: Any?): String = try {
            val id = UUID.fromString(arguments[0].toString())
            "/viewer/${id}"
        } catch (exception: RuntimeException) {
            throw IllegalArgumentException(exception)
        }
    }
}