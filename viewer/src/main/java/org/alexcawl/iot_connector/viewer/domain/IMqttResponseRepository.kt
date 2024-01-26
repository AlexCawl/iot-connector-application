package org.alexcawl.iot_connector.viewer.domain

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.MqttResponseHolder

interface IMqttResponseRepository {
    fun subscribe(endpoint: String): Flow<Result<MqttResponseHolder>>
}