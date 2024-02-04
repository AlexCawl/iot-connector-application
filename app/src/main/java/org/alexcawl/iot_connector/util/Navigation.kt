package org.alexcawl.iot_connector.util

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.includeMainMultiFeatureScreen(
    route: String,
    outerNavController: NavController
) = composable(route) {
    MultiFeatureMainScreen(outerNavController = outerNavController)
}