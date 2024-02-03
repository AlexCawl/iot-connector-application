package org.alexcawl.iot_connector.viewer.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.viewer.data.MqttResponseRepository
import org.alexcawl.iot_connector.viewer.domain.mapper.ThermalMatrixMapper
import org.alexcawl.iot_connector.viewer.domain.mapper.ViewerStateMapper
import org.alexcawl.iot_connector.viewer.data.IMqttResponseRepository
import org.alexcawl.iot_connector.viewer.domain.mapper.IThermalMatrixMapper
import org.alexcawl.iot_connector.viewer.domain.mapper.IViewerStateMapper
import org.alexcawl.iot_connector.viewer.ui.screen.ViewerScreenViewModel

@Module
interface ViewerModule {
    @Binds
    @IntoMap
    @ViewModelKey(ViewerScreenViewModel::class)
    fun bindViewerScreenViewModel(viewModel: ViewerScreenViewModel): ViewModel

    @Binds
    fun bindStateMapper(stateMapper: ViewerStateMapper): IViewerStateMapper

    @Binds
    fun bindMatrixMapper(matrixMapper: ThermalMatrixMapper): IThermalMatrixMapper

    @Binds
    fun bindMqttResponseRepository(repository: MqttResponseRepository): IMqttResponseRepository
}