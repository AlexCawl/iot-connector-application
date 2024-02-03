package org.alexcawl.iot_connector.viewer.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

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