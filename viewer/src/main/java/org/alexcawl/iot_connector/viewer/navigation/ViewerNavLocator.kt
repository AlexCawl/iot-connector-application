package org.alexcawl.iot_connector.viewer.navigation

import java.util.UUID

object ViewerNavLocator {
    const val route: String = "/viewer/{id}"

    fun buildRoute(id: UUID): String = "/viewer/$id"
}