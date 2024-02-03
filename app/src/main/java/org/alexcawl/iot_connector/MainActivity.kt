package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.alexcawl.iot_connector.connection.navigation.ConnectionNavigationNode
import org.alexcawl.iot_connector.profile.navigation.ProfileNavigationNode
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
                    startDestination = "root"
                ) {
                    composable("root") {
                        Column {
                            Button(onClick = { navController.navigate(ConnectionNavigationNode.ConnectionsShowScreen.buildRoute()) }) {
                                Text(text = "connections")
                            }
                            Button(onClick = { navController.navigate(ProfileNavigationNode.ProfileShowScreen.buildRoute()) }) {
                                Text(text = "profiles")
                            }
                        }
                    }
                }
            }
        }
    }

    private companion object {
        @Composable
        fun MainScreen(
            modifier: Modifier = Modifier
        ) {
            val innerNavController = rememberNavController()

            Scaffold(
                topBar = {

                },
                bottomBar = {

                },
                modifier = modifier
            ) { paddingValues: PaddingValues ->
                val paddingModifier = Modifier.padding(paddingValues)
                NavHost(
                    navController = innerNavController,
                    startDestination = "",
                    modifier = paddingModifier
                ) {

                }
            }
        }
    }
}