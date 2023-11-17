package org.alexcawl.iot_connector.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}