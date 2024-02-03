package org.alexcawl.iot_connector.ui.util

interface NavigationNode {
    val route: String

    @Throws(IllegalArgumentException::class)
    fun buildRoute(vararg arguments: Any? = emptyArray()): String
}