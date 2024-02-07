package org.alexcawl.iot_connector.network.mqtt

import android.util.Log
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.common.DEBUG_LOG_TAG
import javax.inject.Inject

class MqttAsyncClientDao @Inject constructor(
    private val holder: IMqttClientHolder<Mqtt5AsyncClient>
) : IMqttClientDao<Mqtt5Publish> {
    override suspend fun publish(endpoint: String, publish: Mqtt5Publish) {
        try {
            val client = holder.client.first().getOrThrow()
            client.publish(publish)
        } catch (exception: RuntimeException) {
            Log.d(DEBUG_LOG_TAG, exception.message.toString())
        }
    }

    override fun subscribe(endpoint: String): Flow<Result<Mqtt5Publish>> = channelFlow {
        holder.client.collect {
            try {
                val client = holder.client.first().getOrThrow()
                client.subscribeWith()
                    .topicFilter(endpoint)
                    .qos(MqttQos.AT_LEAST_ONCE)
                    .callback {
                        launch {
                            send(Result.success(it))
                        }
                    }
                    .send()
            } catch (exception: RuntimeException) {
                send(Result.failure(exception))
            }
        }
    }
}