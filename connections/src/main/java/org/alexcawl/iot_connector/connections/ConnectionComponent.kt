package org.alexcawl.iot_connector.connections

import dagger.Component
import org.alexcawl.iot_connector.connections.dependencies.ConnectionScope

@ConnectionScope
@Component(modules = [ConnectionModule::class])
interface ConnectionComponent