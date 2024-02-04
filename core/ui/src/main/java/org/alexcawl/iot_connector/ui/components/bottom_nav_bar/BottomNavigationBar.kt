package org.alexcawl.iot_connector.ui.components.bottom_nav_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun BottomNavigationBar(
    navController: NavController,
    navigationItems: List<BottomNavigationBarItem>,
    modifier: Modifier = Modifier
) = NavigationBar(
    modifier = modifier
) {
    navigationItems.forEach { item ->
        val backStackEntry by navController.currentBackStackEntryAsState()
        NavigationBarItem(
            selected = backStackEntry?.destination?.route == item.route,
            onClick = {
                navController.navigate(route = item.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = when (backStackEntry?.destination?.route == item.route) {
                        true -> item.selectedIcon
                        false -> item.unselectedIcon
                    },
                    contentDescription = null
                )
            },
            label = {
                Text(text = item.description)
            }
        )
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    val navController = rememberNavController()
    val screens = listOf(
        BottomNavigationBarItem("A", Icons.Default.Info, "Screen A"),
        BottomNavigationBarItem("B", Icons.Default.Search, "Screen B"),
        BottomNavigationBarItem("C", Icons.Default.Create, "Screen C")
    )
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, navigationItems = screens)
        }
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = "A"
        ) {
            composable("A") {
                LoadingScreen {
                    Text(text = "A")
                }
            }
            composable("B") {
                LoadingScreen {
                    Text(text = "B")
                }
            }
            composable("C") {
                LoadingScreen {
                    Text(text = "C")
                }
            }
        }
    }

}