package org.alexcawl.iot_connector.connections.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.alexcawl.iot_connector.connections.DaggerConnectionsComponent
import org.alexcawl.iot_connector.connections.dependencies.ConnectionDependenciesStore
import org.alexcawl.iot_connector.connections.domain.ConnectionsViewModel
import org.alexcawl.iot_connector.connections.domain.ProfileViewModel
import org.alexcawl.iot_connector.ui.util.composeViewModel

fun NavGraphBuilder.connectionsGraph(navController: NavController) {
    composable("connections") {
        val viewModel = composeViewModel(modelClass = ConnectionsViewModel::class.java) {
            DaggerConnectionsComponent.builder()
                .dependencies(ConnectionDependenciesStore.dependencies)
                .build()
                .provideFactory()
        }
        val state by viewModel.state.collectAsState()
        Column {
            Text(text = state)
            Button(onClick = { navController.navigate("profile") }) {
                Text(text = "Navigate to profile")
            }
        }
    }
    composable("profile") {
        val viewModel = composeViewModel(modelClass = ProfileViewModel::class.java) {
            DaggerConnectionsComponent.builder()
                .dependencies(ConnectionDependenciesStore.dependencies)
                .build()
                .provideFactory()
        }
        val state by viewModel.state.collectAsState()
        Column {
            Text(text = "$state")
            Button(onClick = { navController.navigate("connections") }) {
                Text(text = "Navigate to connections")
            }
        }
    }
}