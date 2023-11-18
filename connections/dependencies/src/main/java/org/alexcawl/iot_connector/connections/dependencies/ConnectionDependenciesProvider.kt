package org.alexcawl.iot_connector.connections.dependencies

interface ConnectionDependenciesProvider {
    val dependencies: ConnectionDependencies

    companion object : ConnectionDependenciesProvider by ConnectionDependenciesStore
}