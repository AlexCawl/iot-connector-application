package org.alexcawl.iot_connector.client

import org.alexcawl.iot_connector.client.di.ClientComponent
import org.alexcawl.iot_connector.client.di.ClientDependencies
import org.alexcawl.iot_connector.client.di.DaggerClientComponent
import org.alexcawl.iot_connector.di.ComponentStore
import kotlin.properties.Delegates

object ClientComponentStore : ComponentStore<ClientComponent, ClientDependencies> {
    override var component: ClientComponent by Delegates.notNull()

    override fun inject(dependencies: ClientDependencies) {
        component = DaggerClientComponent.builder()
            .dependencies(dependencies)
            .build()
    }
}