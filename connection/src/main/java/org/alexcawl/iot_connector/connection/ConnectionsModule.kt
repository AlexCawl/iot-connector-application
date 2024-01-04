package org.alexcawl.iot_connector.connection

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.connection.domain.ConnectionsViewModel
import org.alexcawl.iot_connector.connection.domain.ProfileViewModel
import org.alexcawl.iot_connector.di.ViewModelKey

@Module
interface ConnectionsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ConnectionsViewModel::class)
    fun bindConnectionsViewModel(viewModel: ConnectionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}