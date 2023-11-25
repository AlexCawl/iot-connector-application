package org.alexcawl.iot_connector.profile.ui.screen.add.component

import androidx.compose.material3.Text
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.installAddProfileScreen(
    screenRoute: String,
    factory: ViewModelProvider.Factory
) {
    composable(screenRoute) {
        Text(text = screenRoute)
    }
}