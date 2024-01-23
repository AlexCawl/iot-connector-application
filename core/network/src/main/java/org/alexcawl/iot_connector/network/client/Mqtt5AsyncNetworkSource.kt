package org.alexcawl.iot_connector.network.client

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.alexcawl.iot_connector.network.subscribe
import javax.inject.Inject

class Mqtt5AsyncNetworkSource @Inject constructor(
    private val clientSource: IMqttClientSource<Mqtt5AsyncClient>
) : IMqttNetworkSource<Mqtt5Publish> {
    override val networkExceptions: Flow<Throwable?> = clientSource.connectedClient.map {
        it.exceptionOrNull()
    }

    override fun subscribe(endpoint: String): Flow<Result<Mqtt5Publish>> = channelFlow {
        clientSource.connectedClient.collect {
            try {
                val client: Mqtt5AsyncClient = it.getOrThrow()
                client.subscribe(endpoint) { publish ->
                    runBlocking {
                        send(Result.success(publish))
                    }
                }
            } catch (exception: RuntimeException) {
                send(Result.failure(exception))
            }
        }
    }
}