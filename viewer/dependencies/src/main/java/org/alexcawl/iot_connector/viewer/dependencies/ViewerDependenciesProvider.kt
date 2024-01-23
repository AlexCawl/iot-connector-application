package org.alexcawl.iot_connector.viewer.dependencies

interface ViewerDependenciesProvider {
    val dependencies: ViewerDependencies

    companion object : ViewerDependenciesProvider by ViewerDependenciesStore
}