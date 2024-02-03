package org.alexcawl.iot_connector.connection.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.connection.data.ConnectionEntityMapper
import org.alexcawl.iot_connector.connection.data.ConnectionRepository
import org.alexcawl.iot_connector.connection.domain.mapper.ConnectionStateMapper
import org.alexcawl.iot_connector.connection.data.IConnectionEntityMapper
import org.alexcawl.iot_connector.connection.data.IConnectionRepository
import org.alexcawl.iot_connector.connection.data.INetworkStateRepository
import org.alexcawl.iot_connector.connection.data.NetworkStateRepository
import org.alexcawl.iot_connector.connection.domain.mapper.IConnectionStateMapper
import org.alexcawl.iot_connector.connection.ui.show_screen.ShowConnectionsViewModel
import org.alexcawl.iot_connector.connection.ui.update_screen.add_screen.AddConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen.EditConnectionViewModel
import org.alexcawl.iot_connector.di.ViewModelKey

@Module
interface ConnectionsModule {
    @Binds
    fun bindConnectionEntityMapper(mapper: ConnectionEntityMapper): IConnectionEntityMapper

    @Binds
    fun bindConnectionStateMapper(mapper: ConnectionStateMapper): IConnectionStateMapper

    @Binds
    fun bindConnectionRepository(repository: ConnectionRepository): IConnectionRepository

    @Binds
    fun bindNetworkStateRepository(repository: NetworkStateRepository): INetworkStateRepository

    @Binds
    @IntoMap
    @ViewModelKey(ShowConnectionsViewModel::class)
    fun bindShowConnectionsScreenViewModel(viewModel: ShowConnectionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddConnectionViewModel::class)
    fun bindAddConnectionScreenViewModel(viewModel: AddConnectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditConnectionViewModel::class)
    fun bindEditConnectionScreenViewModel(viewModel: EditConnectionViewModel): ViewModel
}