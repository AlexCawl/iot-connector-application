package org.alexcawl.iot_connector.connections.dependencies

import kotlin.properties.Delegates

object ConnectionDependenciesStore : ConnectionDependenciesProvider {
    override var dependencies: ConnectionDependencies by Delegates.notNull()
}