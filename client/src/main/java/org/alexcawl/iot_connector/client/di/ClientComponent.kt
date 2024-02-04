package org.alexcawl.iot_connector.client.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@ClientScope
@Component(modules = [ClientModule::class], dependencies = [ClientDependencies::class])
interface ClientComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: ClientDependencies): Builder

        fun build(): ClientComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}