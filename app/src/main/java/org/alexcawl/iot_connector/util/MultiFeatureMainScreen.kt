package org.alexcawl.iot_connector.util

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.R
import org.alexcawl.iot_connector.client.navigation.ClientNavigationNode
import org.alexcawl.iot_connector.client.navigation.includeClientStatusScreen
import org.alexcawl.iot_connector.connection.navigation.ConnectionNavigationNode
import org.alexcawl.iot_connector.connection.navigation.includeShowConnectionsScreen
import org.alexcawl.iot_connector.profile.navigation.ProfileNavigationNode
import org.alexcawl.iot_connector.profile.navigation.includeShowProfilesScreen
import org.alexcawl.iot_connector.ui.components.bottom_nav_bar.BottomNavigationBar
import org.alexcawl.iot_connector.ui.components.bottom_nav_bar.BottomNavigationBarItem
import org.alexcawl.iot_connector.viewer.navigation.ViewerNavigationNode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiFeatureMainScreen(
    outerNavController: NavController,
    modifier: Modifier = Modifier
) {
    val innerNavController = rememberNavController()
    val destination by innerNavController.currentBackStackEntryAsState()
    val screens = listOf(
        BottomNavigationBarItem(
            route = ConnectionNavigationNode.ConnectionsShowScreen.buildRoute(),
            selectedIcon = Icons.Filled.Call,
            description = stringResource(id = R.string.connections_screen),
            unselectedIcon = Icons.Outlined.Call
        ),
        BottomNavigationBarItem(
            route = ClientNavigationNode.ClientStatusScreen.buildRoute(),
            selectedIcon = Icons.Filled.Search,
            description = stringResource(id = R.string.client_screen),
            unselectedIcon = Icons.Outlined.Search
        ),
        BottomNavigationBarItem(
            route = ProfileNavigationNode.ProfileShowScreen.buildRoute(),
            selectedIcon = Icons.Filled.AccountBox,
            description = stringResource(id = R.string.profiles_screen),
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
                    AnimatedContent(
                        targetState = screens
                            .find { it.route == destination?.destination?.route }
                            ?.description,
                        label = MAIN_SCREEN_TITLE_LABEL
                    ) {
                        when (it) {
                            null -> Unit
                            else -> Text(text = it)
                        }
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
            startDestination = ClientNavigationNode.ClientStatusScreen.buildRoute(),
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
            includeClientStatusScreen(
                route = ClientNavigationNode.ClientStatusScreen.route
            )
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

private const val MAIN_SCREEN_TITLE_LABEL: String = "MAIN_TITLE"