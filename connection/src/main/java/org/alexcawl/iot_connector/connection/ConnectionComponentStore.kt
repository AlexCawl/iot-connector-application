package org.alexcawl.iot_connector.connection

import org.alexcawl.iot_connector.connection.di.ConnectionComponent
import org.alexcawl.iot_connector.connection.di.ConnectionDependencies
import org.alexcawl.iot_connector.connection.di.DaggerConnectionComponent
import org.alexcawl.iot_connector.di.ComponentStore
import kotlin.properties.Delegates

object ConnectionComponentStore : ComponentStore<ConnectionComponent, ConnectionDependencies> {
    override var component: ConnectionComponent by Delegates.notNull()

    override fun inject(dependencies: ConnectionDependencies) {
        component = DaggerConnectionComponent.builder()
            .dependencies(dependencies)
            .build()
    }
}