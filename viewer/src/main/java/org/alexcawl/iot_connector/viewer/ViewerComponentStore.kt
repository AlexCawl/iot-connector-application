package org.alexcawl.iot_connector.viewer

import org.alexcawl.iot_connector.di.ComponentStore
import org.alexcawl.iot_connector.viewer.di.DaggerViewerComponent
import org.alexcawl.iot_connector.viewer.di.ViewerComponent
import org.alexcawl.iot_connector.viewer.di.ViewerDependencies
import kotlin.properties.Delegates

object ViewerComponentStore : ComponentStore<ViewerComponent, ViewerDependencies> {
    override var component: ViewerComponent by Delegates.notNull()

    override fun inject(dependencies: ViewerDependencies) {
        component = DaggerViewerComponent.builder()
            .dependencies(dependencies)
            .build()
    }
}