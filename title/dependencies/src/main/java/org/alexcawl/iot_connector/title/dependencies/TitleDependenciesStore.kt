package org.alexcawl.iot_connector.title.dependencies

import kotlin.properties.Delegates

object TitleDependenciesStore : TitleDependenciesProvider {
    override var dependencies: TitleDependencies by Delegates.notNull()
}