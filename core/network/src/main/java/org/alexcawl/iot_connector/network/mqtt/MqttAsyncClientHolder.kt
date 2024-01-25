package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.alexcawl.iot_connector.common.exceptions.LoadingPlaceholderException
import java.util.UUID
import javax.inject.Inject

class MqttAsyncClientHolder @Inject constructor(
    private val factory: IMqttClientFactory<Mqtt5AsyncClient>,
    private val key: Flow<UUID>
) : IMqttClientHolder<Mqtt5AsyncClient> {
    private val _scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val _client: MutableStateFlow<Result<Mqtt5AsyncClient>> =
        MutableStateFlow(Result.failure(LoadingPlaceholderException))

    override val client: StateFlow<Result<Mqtt5AsyncClient>> = _client.asStateFlow()

    init {
        _scope.launch {
            factory.client.combine(key) { clientResult: Result<Mqtt5AsyncClient>, _: UUID -> clientResult }
                .collect { clientResult: Result<Mqtt5AsyncClient> ->
                    try {
                        val inactiveClient: Mqtt5AsyncClient = clientResult.getOrThrow()
                        inactiveClient.connect()
                            .whenComplete { connection: Mqtt5ConnAck?, exception: Throwable? ->
                                runBlocking {
                                    _client.emit(
                                        when {
                                            connection != null -> Result.success(inactiveClient)
                                            exception != null -> Result.failure(exception)
                                            else -> Result.failure(IllegalStateException())
                                        }
                                    )
                                }
                            }
                    } catch (exception: RuntimeException) {
                        _client.emit(Result.failure(exception))
                    }
                }
        }
    }
}