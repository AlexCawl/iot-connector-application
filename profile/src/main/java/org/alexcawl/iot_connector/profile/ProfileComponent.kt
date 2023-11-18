package org.alexcawl.iot_connector.profile

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependencies
import org.alexcawl.iot_connector.profile.dependencies.ProfileScope

@ProfileScope
@Component(modules = [ProfileModule::class], dependencies = [ProfileDependencies::class])
interface ProfileComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: ProfileDependencies): Builder

        fun build(): ProfileComponent
    }

    fun provideFactory(): ViewModelProvider.Factory
}