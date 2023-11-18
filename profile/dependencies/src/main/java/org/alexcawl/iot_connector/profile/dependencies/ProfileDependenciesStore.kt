package org.alexcawl.iot_connector.profile.dependencies

import kotlin.properties.Delegates

object ProfileDependenciesStore : ProfileDependenciesProvider {
    override var dependencies: ProfileDependencies by Delegates.notNull()
}