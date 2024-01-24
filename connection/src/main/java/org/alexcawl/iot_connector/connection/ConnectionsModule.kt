package org.alexcawl.iot_connector.connection

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.connection.data.ConnectionEntityMapper
import org.alexcawl.iot_connector.connection.data.ConnectionRepository
import org.alexcawl.iot_connector.connection.data.ConnectionStateMapper
import org.alexcawl.iot_connector.connection.domain.IConnectionEntityMapper
import org.alexcawl.iot_connector.connection.domain.IConnectionRepository
import org.alexcawl.iot_connector.connection.domain.IConnectionStateMapper
import org.alexcawl.iot_connector.connection.ui.screen.show.ShowConnectionsViewModel
import org.alexcawl.iot_connector.connection.ui.screen.update.add.AddConnectionViewModel
import org.alexcawl.iot_connector.connection.ui.screen.update.edit.EditConnectionViewModel
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