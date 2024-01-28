package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.connection.ui.ConnectionNavLocator
import org.alexcawl.iot_connector.connection.ui.connectionNavigation
import org.alexcawl.iot_connector.profile.ui.profileNavigation
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.viewer.ui.ViewerNavLocator
import org.alexcawl.iot_connector.viewer.ui.viewerNavigation
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
                    startDestination = ConnectionNavLocator.ConnectionsShowScreen.route
                ) {
                    profileNavigation(navController)
                    connectionNavigation(navController) {
                        navController.navigate(
                            ViewerNavLocator.buildRoute(
                                it
                            )
                        )
                    }
                    viewerNavigation(navController)
                }
            }
        }
    }
}