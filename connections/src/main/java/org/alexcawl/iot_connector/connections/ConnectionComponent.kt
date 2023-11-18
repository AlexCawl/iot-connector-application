package org.alexcawl.iot_connector.connections

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import org.alexcawl.iot_connector.connections.dependencies.ConnectionDependencies
import org.alexcawl.iot_connector.connections.dependencies.ConnectionScope

@ConnectionScope
@Component(modules = [ConnectionModule::class], dependencies = [ConnectionDependencies::class])
interface ConnectionComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: ConnectionDependencies): Builder

        fun build(): ConnectionComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}