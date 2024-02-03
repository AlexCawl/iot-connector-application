package org.alexcawl.iot_connector.profile.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

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