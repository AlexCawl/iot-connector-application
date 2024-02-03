package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.connection.ui.ConnectionNavLocator
import org.alexcawl.iot_connector.connection.ui.connectionNavigation
import org.alexcawl.iot_connector.profile.navigation.ProfileNavLocator
import org.alexcawl.iot_connector.profile.navigation.profileNavigation
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
                    startDestination = "root"
                ) {
                    composable("root") {
                        Column {
                            Button(onClick = { navController.navigate(ConnectionNavLocator.ConnectionsShowScreen.route) }) {
                                Text(text = "connections")
                            }
                            Button(onClick = { navController.navigate(ProfileNavLocator.ProfileShowScreen.route) }) {
                                Text(text = "profiles")
                            }
                        }
                    }
                    profileNavigation(navController)
                    connectionNavigation(
                        navController = navController,
                        onNavigateToViewConnection = {
                            navController.navigate(ViewerNavLocator.buildRoute(it))
                        }
                    )
                    viewerNavigation(navController)
                }
            }
        }
    }
}