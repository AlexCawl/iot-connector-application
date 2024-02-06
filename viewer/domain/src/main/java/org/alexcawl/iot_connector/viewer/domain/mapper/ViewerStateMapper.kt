package org.alexcawl.iot_connector.viewer.domain.mapper

import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import org.alexcawl.iot_connector.common.model.ThermalResponse
import org.alexcawl.iot_connector.ui.state.ViewerState
import org.alexcawl.iot_connector.ui.state.data.TextRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ThermalDataRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ThermalMatrixRepresentationModel
import javax.inject.Inject

class ViewerStateMapper @Inject constructor(
    private val matrixMapper: IThermalMatrixMapper
) : IViewerStateMapper {
    override fun map(from: MqttResponse): ViewerState = with(from) {
        val representations = payloads.flatMap { payload: MqttResponsePayload ->
            when (payload) {
                is ThermalResponse -> listOf(
                    runCatching {
                        ThermalDataRepresentationModel(
                            device = payload.device,
                            sensorType = payload.sensorType,
                            temperatures = payload.temperatures.map { it.toFloat() / 100f }
                                .toTypedArray()
                        )
                    },
                    matrixMapper.map(payload.temperatures).map {
                        ThermalMatrixRepresentationModel(it)
                    }
                ).mapNotNull { it.getOrNull() }
                else -> listOf(TextRepresentationModel(payload.raw))
            }
        }
        ViewerState(
            id = id,
            endpoint = endpoint,
            name = name,
            viewsData = representations.sortedBy { it.priority }
        )
    }
}