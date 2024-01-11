package org.alexcawl.iot_connector.network

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import org.alexcawl.iot_connector.network.dependencies.NetworkDependencies
import org.alexcawl.iot_connector.network.dependencies.NetworkScope

@NetworkScope
@Component(modules = [NetworkModule::class], dependencies = [NetworkDependencies::class])
interface NetworkComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: NetworkDependencies): Builder

        fun build(): NetworkComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}