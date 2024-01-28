package org.alexcawl.iot_connector.viewer.data

import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.network.dto.ThermalSmallResponse
import org.alexcawl.iot_connector.network.dto.TextResponse
import org.alexcawl.iot_connector.network.dto.ThermalLargeResponse
import org.alexcawl.iot_connector.network.dto.UserParamsResponse
import org.alexcawl.iot_connector.ui.state.ViewerState
import org.alexcawl.iot_connector.ui.state.data.DefaultRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ParametersRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.TextRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ThermalRepresentationModel
import org.alexcawl.iot_connector.viewer.domain.IViewerStateMapper
import javax.inject.Inject

class ViewerStateMapper @Inject constructor() : IViewerStateMapper {
    override fun map(from: MqttResponse): ViewerState = with(from) {
        ViewerState(
            id = id,
            endpoint = endpoint,
            name = name,
            viewsData = payloads.map {
                when (it) {
                    is TextResponse -> TextRepresentationModel(it.text)
                    is UserParamsResponse -> ParametersRepresentationModel(it.params)
                    is ThermalLargeResponse -> ThermalRepresentationModel(
                        device = it.device,
                        sensorType = it.sensorType,
                        temperatures = mapSmallThermalMatrix(it.temperatures)
                    )

                    is ThermalSmallResponse -> ThermalRepresentationModel(
                        device = it.device,
                        sensorType = it.sensorType,
                        temperatures = mapLargeThermalMatrix(it.temperatures)
                    )

                    else -> DefaultRepresentationModel(it.raw)
                }
            }.sortedBy { it.priority }
        )
    }

    private companion object {
        fun mapSmallThermalMatrix(temperatures: List<Int>): Array<Array<Int>> = emptyArray()

        fun mapLargeThermalMatrix(temperatures: List<Int>): Array<Array<Int>> = Array(24) {
            Array(32) { 0 }
        }.also {
            var counter = 0
            it.forEachIndexed { i, array ->
                array.forEachIndexed { j, _ ->
                    it[i][j] = temperatures[counter++]
                }
            }
        }
    }
}