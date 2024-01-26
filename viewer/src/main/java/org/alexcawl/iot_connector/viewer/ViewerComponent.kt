package org.alexcawl.iot_connector.viewer

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import org.alexcawl.iot_connector.viewer.dependencies.ViewerDependencies
import org.alexcawl.iot_connector.viewer.dependencies.ViewerScope

@ViewerScope
@Component(modules = [ViewerModule::class], dependencies = [ViewerDependencies::class])
interface ViewerComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: ViewerDependencies): Builder

        fun build(): ViewerComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}