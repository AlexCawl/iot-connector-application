package org.alexcawl.iot_connector.viewer.domain.mapper

import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.common.model.ThermalResponse
import org.alexcawl.iot_connector.ui.state.ViewerState
import org.alexcawl.iot_connector.ui.state.data.TextRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ThermalRepresentationModel
import javax.inject.Inject

class ViewerStateMapper @Inject constructor(
    private val matrixMapper: IThermalMatrixMapper
) : IViewerStateMapper {
    override fun map(from: MqttResponse): ViewerState = with(from) {
        ViewerState(
            id = id,
            endpoint = endpoint,
            name = name,
            viewsData = payloads.mapNotNull {
                when (it) {
                    is ThermalResponse -> try {
                        ThermalRepresentationModel(
                            device = it.device,
                            sensorType = it.sensorType,
                            temperatures = matrixMapper.map(it.temperatures).getOrThrow()
                        )
                    } catch (exception: IllegalArgumentException) {
                        null
                    }
                    else -> TextRepresentationModel(it.raw)
                }
            }.sortedBy { it.priority }
        )
    }
}