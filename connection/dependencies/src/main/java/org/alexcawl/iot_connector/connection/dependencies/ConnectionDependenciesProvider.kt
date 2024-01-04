package org.alexcawl.iot_connector.connection.dependencies

interface ConnectionDependenciesProvider {
    val dependencies: ConnectionDependencies

    companion object : ConnectionDependenciesProvider by ConnectionDependenciesStore
}