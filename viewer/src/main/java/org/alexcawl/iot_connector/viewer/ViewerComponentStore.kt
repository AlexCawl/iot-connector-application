package org.alexcawl.iot_connector.viewer

import org.alexcawl.iot_connector.di.ComponentStore
import org.alexcawl.iot_connector.viewer.di.DaggerViewerComponent
import org.alexcawl.iot_connector.viewer.di.ViewerComponent
import org.alexcawl.iot_connector.viewer.di.ViewerDependencies

object ViewerComponentStore : ComponentStore<ViewerComponent, ViewerDependencies> {
    override lateinit var dependencies: ViewerDependencies

    override val component: ViewerComponent
        get() = DaggerViewerComponent.builder()
            .dependencies(dependencies)
            .build()
}