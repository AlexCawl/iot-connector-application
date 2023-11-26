package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.profile.ui.ProfileNavLocator
import org.alexcawl.iot_connector.profile.ui.installProfileNavigation
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
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
                    startDestination = ProfileNavLocator.ProfileShowScreen.route
                ) {
                    installProfileNavigation(navController)
                }
            }
        }
    }
}