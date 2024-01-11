package org.alexcawl.iot_connector.network.dependencies

interface NetworkDependenciesProvider {
    val dependencies: NetworkDependencies

    companion object : NetworkDependenciesProvider by NetworkDependenciesStore
}