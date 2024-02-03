package org.alexcawl.iot_connector.viewer.data

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.MqttResponse
import java.util.UUID

interface IMqttResponseRepository {
    fun subscribe(connectionId: UUID): Flow<Result<MqttResponse>>
}