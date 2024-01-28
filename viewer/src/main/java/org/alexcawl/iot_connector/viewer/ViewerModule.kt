package org.alexcawl.iot_connector.viewer

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.viewer.data.MqttResponseRepository
import org.alexcawl.iot_connector.viewer.data.ViewerStateMapper
import org.alexcawl.iot_connector.viewer.domain.IMqttResponseRepository
import org.alexcawl.iot_connector.viewer.domain.IViewerStateMapper
import org.alexcawl.iot_connector.viewer.ui.screens.ViewerScreenViewModel

@Module
interface ViewerModule {
    @Binds
    @IntoMap
    @ViewModelKey(ViewerScreenViewModel::class)
    fun bindViewerScreenViewModel(viewModel: ViewerScreenViewModel): ViewModel

    @Binds
    fun bindStateMapper(stateMapper: ViewerStateMapper): IViewerStateMapper

    @Binds
    fun bindMqttResponseRepository(repository: MqttResponseRepository): IMqttResponseRepository
}