package org.alexcawl.iot_connector.viewer.dependencies

import kotlin.properties.Delegates

object ViewerDependenciesStore : ViewerDependenciesProvider {
    override var dependencies: ViewerDependencies by Delegates.notNull()
}