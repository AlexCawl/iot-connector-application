package org.alexcawl.iot_connector.viewer.data

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.MqttResponseHolder
import org.alexcawl.iot_connector.network.mqtt.IMqttClientDao
import org.alexcawl.iot_connector.network.transcriber.IMqttResponseTranscriber
import org.alexcawl.iot_connector.viewer.domain.IMqttResponseRepository
import javax.inject.Inject

class MqttResponseRepository @Inject constructor(
    private val clientDao: IMqttClientDao<Mqtt5Publish>,
    private val transcribers: @JvmSuppressWildcards Set<IMqttResponseTranscriber<Mqtt5Publish>>
): IMqttResponseRepository {
    override fun subscribe(endpoint: String): Flow<Result<MqttResponseHolder>> {
        TODO("Not yet implemented")
    }
}