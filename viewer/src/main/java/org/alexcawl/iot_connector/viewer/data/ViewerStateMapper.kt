package org.alexcawl.iot_connector.viewer.data

import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.network.dto.ThermalSmallResponse
import org.alexcawl.iot_connector.network.dto.TextResponse
import org.alexcawl.iot_connector.network.dto.ThermalLargeResponse
import org.alexcawl.iot_connector.network.dto.UserParamsResponse
import org.alexcawl.iot_connector.ui.state.ViewerState
import org.alexcawl.iot_connector.ui.state.data.DefaultViewerData
import org.alexcawl.iot_connector.ui.state.data.ParamsViewerData
import org.alexcawl.iot_connector.ui.state.data.TextViewerData
import org.alexcawl.iot_connector.ui.state.data.ThermalViewerData
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
                    is TextResponse -> TextViewerData(it.text)
                    is UserParamsResponse -> ParamsViewerData(it.params)
                    is ThermalLargeResponse -> ThermalViewerData(
                        device = it.device,
                        sensorType = it.sensorType,
                        temperatures = mapSmallThermalMatrix(it.temperatures)
                    )

                    is ThermalSmallResponse -> ThermalViewerData(
                        device = it.device,
                        sensorType = it.sensorType,
                        temperatures = mapLargeThermalMatrix(it.temperatures)
                    )

                    else -> DefaultViewerData(it.raw)
                }
            }
        )
    }

    private companion object {
        fun mapSmallThermalMatrix(temperatures: List<Int>): Array<Array<Int>> = TODO()

        fun mapLargeThermalMatrix(temperatures: List<Int>): Array<Array<Int>> = TODO()
    }
}