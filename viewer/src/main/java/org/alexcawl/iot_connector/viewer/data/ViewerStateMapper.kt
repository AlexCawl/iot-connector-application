package org.alexcawl.iot_connector.viewer.data

import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.network.dto.TextResponse
import org.alexcawl.iot_connector.network.dto.ThermalResponse
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
                    is ThermalResponse -> ThermalRepresentationModel(
                        device = it.device,
                        sensorType = it.sensorType,
                        temperatures = mapThermalMatrix(it.temperatures)
                    )
                    else -> DefaultRepresentationModel(it.raw)
                }
            }.sortedBy { it.priority }
        )
    }

    private companion object {
        fun mapThermalMatrix(temperatures: List<Int>): Array<Array<Int>> = try {
            Array(32) {
                Array(24) { 0 }
            }.also {
                var counter = 0
                it.forEachIndexed { i, array ->
                    array.forEachIndexed { j, _ ->
                        it[i][j] = temperatures[counter++]
                    }
                }
            }
        } catch (exception: IndexOutOfBoundsException) {
            Array(8) {
                Array(8) { 0 }
            }.also {
                var counter = 0
                it.forEachIndexed { i, array ->
                    array.forEachIndexed { j, _ ->
                        it[i][j] = temperatures[counter++]
                    }
                }
            }
        } catch (exception: IndexOutOfBoundsException) {
            emptyArray()
        }
    }
}