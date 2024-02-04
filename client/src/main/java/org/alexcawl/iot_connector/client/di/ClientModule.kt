package org.alexcawl.iot_connector.client.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.client.data.INetworkStateRepository
import org.alexcawl.iot_connector.client.data.NetworkStateRepository
import org.alexcawl.iot_connector.client.ui.status_screen.ClientStatusScreenViewModel
import org.alexcawl.iot_connector.di.ViewModelKey

@Module
interface ClientModule {
    @Binds
    fun bindNetworkStateRepository(repository: NetworkStateRepository): INetworkStateRepository

    @Binds
    @IntoMap
    @ViewModelKey(ClientStatusScreenViewModel::class)
    fun bindStatusScreenViewModel(viewModel: ClientStatusScreenViewModel): ViewModel
}