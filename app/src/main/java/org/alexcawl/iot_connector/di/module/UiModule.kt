package org.alexcawl.iot_connector.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import org.alexcawl.iot_connector.di.ViewModelFactory

@Module
interface UiModule {
    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}