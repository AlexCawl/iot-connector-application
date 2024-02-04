package org.alexcawl.iot_connector.client.navigation

import org.alexcawl.iot_connector.ui.util.NavigationNode

sealed interface ClientNavigationNode : NavigationNode {
    data object ClientStatusScreen : ClientNavigationNode {
        override val route: String = "/client/status"

        override fun buildRoute(vararg arguments: Any?): String = route
    }
}