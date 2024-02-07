package org.alexcawl.iot_connector.connection

import org.alexcawl.iot_connector.connection.di.ConnectionComponent
import org.alexcawl.iot_connector.connection.di.ConnectionDependencies
import org.alexcawl.iot_connector.connection.di.DaggerConnectionComponent
import org.alexcawl.iot_connector.di.ComponentStore

object ConnectionComponentStore : ComponentStore<ConnectionComponent, ConnectionDependencies> {
    override lateinit var dependencies: ConnectionDependencies

    override val component: ConnectionComponent
        get() = DaggerConnectionComponent.builder()
            .dependencies(dependencies)
            .build()
}