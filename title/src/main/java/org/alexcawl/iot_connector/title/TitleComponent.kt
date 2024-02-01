package org.alexcawl.iot_connector.title

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import org.alexcawl.iot_connector.title.dependencies.TitleDependencies
import org.alexcawl.iot_connector.title.dependencies.TitleScope

@TitleScope
@Component(modules = [TitleModule::class], dependencies = [TitleDependencies::class])
interface TitleComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: TitleDependencies): Builder

        fun build(): TitleComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}