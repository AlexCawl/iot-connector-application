package org.alexcawl.iot_connector.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.MainViewModel
import org.alexcawl.iot_connector.network.NetworkModule

@Module(includes = [ViewModelModule::class, NetworkModule::class])
interface ApplicationModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}