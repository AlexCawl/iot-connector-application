package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.connection.navigation.ConnectionNavigationNode
import org.alexcawl.iot_connector.connection.navigation.includeAddConnectionScreen
import org.alexcawl.iot_connector.connection.navigation.includeEditConnectionScreen
import org.alexcawl.iot_connector.connection.navigation.includeShowConnectionsScreen
import org.alexcawl.iot_connector.profile.navigation.ProfileNavigationNode
import org.alexcawl.iot_connector.profile.navigation.includeAddProfileScreen
import org.alexcawl.iot_connector.profile.navigation.includeEditProfileScreen
import org.alexcawl.iot_connector.profile.navigation.includeShowProfilesScreen
import org.alexcawl.iot_connector.ui.components.bottom_nav_bar.BottomNavigationBar
import org.alexcawl.iot_connector.ui.components.bottom_nav_bar.BottomNavigationBarItem
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.viewer.navigation.ViewerNavigationNode
import org.alexcawl.iot_connector.viewer.navigation.includeViewerScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as IoTConnectorApplication).applicationComponent.inject(this)
        setContent {
            IoTConnectorTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen(outerNavController = navController)
                    }
                    includeAddProfileScreen(
                        route = ProfileNavigationNode.ProfileAddScreen.route,
                        onNavigateBack = navController::navigateUp
                    )
                    includeEditProfileScreen(
                        route = ProfileNavigationNode.ProfileEditScreen.route,
                        onNavigateBack = navController::navigateUp,
                        onNavigateWithException = { navController.navigateUp() }
                    )
                    includeAddConnectionScreen(
                        route = ConnectionNavigationNode.ConnectionAddScreen.route,
                        onNavigateBack = navController::navigateUp
                    )
                    includeEditConnectionScreen(
                        route = ConnectionNavigationNode.ConnectionEditScreen.route,
                        onNavigateBack = navController::navigateUp,
                        onNavigateWithException = { navController.navigateUp() }
                    )
                    includeViewerScreen(
                        route = ViewerNavigationNode.MainViewer.route,
                        onNavigateBack = navController::navigateUp,
                        onNavigateWithException = { navController.navigateUp() }
                    )
                }
            }
        }
    }

    private companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun MainScreen(
            outerNavController: NavController,
            modifier: Modifier = Modifier
        ) {
            val innerNavController = rememberNavController()
            val destination by innerNavController.currentBackStackEntryAsState()
            val screens = listOf(
                BottomNavigationBarItem(
                    route = ConnectionNavigationNode.ConnectionsShowScreen.route,
                    selectedIcon = Icons.Filled.Call,
                    description = "Connections",
                    unselectedIcon = Icons.Outlined.Call
                ),
                BottomNavigationBarItem(
                    route = "root",
                    selectedIcon = Icons.Filled.Search,
                    description = "Zeroconf",
                    unselectedIcon = Icons.Outlined.Search
                ),
                BottomNavigationBarItem(
                    route = ProfileNavigationNode.ProfileShowScreen.route,
                    selectedIcon = Icons.Filled.AccountBox,
                    description = "Profiles",
                    unselectedIcon = Icons.Outlined.AccountBox
                )
            )
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        title = {
                            screens.find { it.route == destination?.destination?.route }
                                ?.description
                                ?.let {
                                    Text(
                                        text = it
                                    )
                                }
                        }
                    )
                },
                bottomBar = {
                    BottomNavigationBar(
                        navController = innerNavController,
                        navigationItems = screens
                    )
                },
                modifier = modifier
            ) { paddingValues: PaddingValues ->
                val paddingModifier = Modifier.padding(paddingValues)
                NavHost(
                    navController = innerNavController,
                    startDestination = "root",
                    modifier = paddingModifier
                ) {
                    includeShowConnectionsScreen(
                        route = ConnectionNavigationNode.ConnectionsShowScreen.route,
                        onAddConnectionAction = {
                            outerNavController.navigate(
                                ConnectionNavigationNode.ConnectionAddScreen.buildRoute()
                            )
                        },
                        onEditConnectionAction = {
                            outerNavController.navigate(
                                ConnectionNavigationNode.ConnectionEditScreen.buildRoute(it)
                            )
                        },
                        onViewConnectionAction = {
                            outerNavController.navigate(
                                ViewerNavigationNode.MainViewer.buildRoute(it)
                            )
                        }
                    )
                    composable("root") {
                        Text(text = "TODO")
                    }
                    includeShowProfilesScreen(
                        route = ProfileNavigationNode.ProfileShowScreen.route,
                        onAddProfileAction = {
                            outerNavController.navigate(
                                ProfileNavigationNode.ProfileAddScreen.buildRoute()
                            )
                        },
                        onEditProfileAction = {
                            outerNavController.navigate(
                                ProfileNavigationNode.ProfileEditScreen.buildRoute(it)
                            )
                        }
                    )
                }
            }
        }
    }
}