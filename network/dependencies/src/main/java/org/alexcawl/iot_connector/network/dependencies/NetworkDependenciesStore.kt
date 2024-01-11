package org.alexcawl.iot_connector.network.dependencies

import kotlin.properties.Delegates

object NetworkDependenciesStore : NetworkDependenciesProvider {
    override var dependencies: NetworkDependencies by Delegates.notNull()
}