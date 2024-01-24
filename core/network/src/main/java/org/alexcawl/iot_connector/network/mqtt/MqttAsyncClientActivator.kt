package org.alexcawl.iot_connector.network.mqtt

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.alexcawl.iot_connector.common.util.Product
import org.alexcawl.iot_connector.common.util.Product.Companion.ofFailure
import org.alexcawl.iot_connector.common.util.Product.Companion.ofSuccess
import java.util.UUID
import javax.inject.Inject

class MqttAsyncClientActivator @Inject constructor(
    private val client: Flow<@JvmSuppressWildcards Product<Mqtt5AsyncClient>>,
    private val key: Flow<UUID>
) : IMqttClientActivator<Mqtt5AsyncClient> {
    private val mutableActivatedClient: MutableStateFlow<Product<Mqtt5AsyncClient>> =
        MutableStateFlow(Product.Empty)

    override val activatedClient: StateFlow<Product<Mqtt5AsyncClient>> =
        mutableActivatedClient.asStateFlow()

    private val _activatedClient: Flow<Product<Mqtt5AsyncClient>> = channelFlow {
        client.combine(key) { clientResult: Product<Mqtt5AsyncClient>, _: UUID -> clientResult }
            .collect { clientResult: Product<Mqtt5AsyncClient> ->
                when (clientResult) {
                    is Product.Empty -> send(Product.Empty)
                    is Product.Failure -> send(ofFailure(clientResult.throwable))
                    is Product.Success -> try {
                        val client: Mqtt5AsyncClient = clientResult.value
                        client.connect()
                            .whenComplete { connection: Mqtt5ConnAck?, exception: Throwable? ->
                                runBlocking {
                                    send(
                                        when {
                                            connection != null -> ofSuccess(client)
                                            exception != null -> ofFailure(exception)
                                            else -> ofFailure(IllegalStateException())
                                        }
                                    )
                                }
                            }
                    } catch (exception: RuntimeException) {
                        runBlocking {
                            send(ofFailure(exception))
                        }
                    }
                }
            }
    }

    init {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            _activatedClient.collect { clientResult: Product<Mqtt5AsyncClient> ->
                mutableActivatedClient.emit(clientResult)
            }
        }
    }
}