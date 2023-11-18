package org.alexcawl.iot_connector.connections

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.connections.domain.ConnectionsViewModel
import org.alexcawl.iot_connector.connections.domain.ProfileViewModel
import org.alexcawl.iot_connector.di.ViewModelKey

@Module
interface ConnectionModule {
    @Binds
    @IntoMap
    @ViewModelKey(ConnectionsViewModel::class)
    fun bindConnectionsViewModel(viewModel: ConnectionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}