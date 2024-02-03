package org.alexcawl.iot_connector.connection.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@ConnectionScope
@Component(modules = [ConnectionsModule::class], dependencies = [ConnectionDependencies::class])
interface ConnectionComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: ConnectionDependencies): Builder

        fun build(): ConnectionComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}