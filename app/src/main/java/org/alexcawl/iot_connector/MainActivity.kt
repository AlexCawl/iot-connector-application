package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.connection.navigation.ConnectionNavigationNode
import org.alexcawl.iot_connector.connection.navigation.includeAddConnectionScreen
import org.alexcawl.iot_connector.connection.navigation.includeEditConnectionScreen
import org.alexcawl.iot_connector.profile.navigation.ProfileNavigationNode
import org.alexcawl.iot_connector.profile.navigation.includeAddProfileScreen
import org.alexcawl.iot_connector.profile.navigation.includeEditProfileScreen
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.util.includeMainMultiFeatureScreen
import org.alexcawl.iot_connector.viewer.navigation.ViewerNavigationNode
import org.alexcawl.iot_connector.viewer.navigation.includeViewerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            IoTConnectorTheme {
                val navController = rememberNavController()
                val startDestination = "main"

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    includeMainMultiFeatureScreen(
                        route = startDestination,
                        outerNavController = navController
                    )
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
}