package org.alexcawl.iot_connector.connection.dependencies

import kotlin.properties.Delegates

object ConnectionDependenciesStore : ConnectionDependenciesProvider {
    override var dependencies: ConnectionDependencies by Delegates.notNull()
}