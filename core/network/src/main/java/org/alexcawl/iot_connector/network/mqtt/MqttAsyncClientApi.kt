package org.alexcawl.iot_connector.network.mqtt

import android.util.Log
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.alexcawl.iot_connector.common.DEBUG_LOG_TAG
import org.alexcawl.iot_connector.common.util.Product
import org.alexcawl.iot_connector.network.subscribe
import javax.inject.Inject

class MqttAsyncClientApi @Inject constructor(
    private val client: StateFlow<@JvmSuppressWildcards Product<Mqtt5AsyncClient>>
) : IMqttClientApi<Mqtt5Publish> {
    override suspend fun publish(endpoint: String, publish: Mqtt5Publish) {
        try {
            when (val currentClient = client.first()) {
                is Product.Success -> currentClient.value.publish(publish)
                else -> Unit
            }
        } catch (exception: RuntimeException) {
            Log.d(DEBUG_LOG_TAG, exception.message.toString())
        }
    }

    override fun subscribe(endpoint: String): Flow<Result<Mqtt5Publish>> = channelFlow {
        client.collect {
            try {
                when (val client = it) {
                    is Product.Empty -> Unit
                    is Product.Failure -> Result.failure<Mqtt5Publish>(client.throwable)
                    is Product.Success -> client.value.subscribe(endpoint) { publish ->
                        runBlocking {
                            send(Result.success(publish))
                        }
                    }
                }
            } catch (exception: RuntimeException) {
                send(Result.failure(exception))
            }
        }
    }
}