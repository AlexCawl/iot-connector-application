package org.alexcawl.iot_connector.ui.components.bottom_nav_bar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class BottomNavigationBarItem(
    val route: String,
    val selectedIcon: ImageVector,
    val description: String,
    val unselectedIcon: ImageVector = selectedIcon
)
