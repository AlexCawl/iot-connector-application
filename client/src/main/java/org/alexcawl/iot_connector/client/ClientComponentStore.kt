package org.alexcawl.iot_connector.client

import org.alexcawl.iot_connector.client.di.ClientComponent
import org.alexcawl.iot_connector.client.di.ClientDependencies
import org.alexcawl.iot_connector.client.di.DaggerClientComponent
import org.alexcawl.iot_connector.di.ComponentStore

object ClientComponentStore : ComponentStore<ClientComponent, ClientDependencies> {
    override lateinit var dependencies: ClientDependencies

    override val component: ClientComponent
        get() = DaggerClientComponent.builder()
            .dependencies(dependencies)
            .build()
}