package org.alexcawl.iot_connector.connection

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import org.alexcawl.iot_connector.connection.dependencies.ConnectionDependencies
import org.alexcawl.iot_connector.connection.dependencies.ConnectionScope

@ConnectionScope
@Component(modules = [ConnectionsModule::class], dependencies = [ConnectionDependencies::class])
interface ConnectionsComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: ConnectionDependencies): Builder

        fun build(): ConnectionsComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}