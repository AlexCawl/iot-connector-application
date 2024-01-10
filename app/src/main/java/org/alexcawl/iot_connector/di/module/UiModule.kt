package org.alexcawl.iot_connector.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import org.alexcawl.iot_connector.di.ViewModelFactory
import org.alexcawl.iot_connector.ui.mappers.IProfileStateMapper
import org.alexcawl.iot_connector.ui.mappers.impl.ProfileStateMapper

@Module
interface UiModule {
    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindProfileStateMapper(mapper: ProfileStateMapper): IProfileStateMapper
}