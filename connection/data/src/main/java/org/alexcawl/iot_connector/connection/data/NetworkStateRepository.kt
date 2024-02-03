package org.alexcawl.iot_connector.connection.data

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.exceptions.LoadingPlaceholderException
import org.alexcawl.iot_connector.network.mqtt.IMqttClientHolder
import javax.inject.Inject

class NetworkStateRepository @Inject constructor(
    private val holder: IMqttClientHolder<Mqtt5AsyncClient>
) : INetworkStateRepository {
    override fun getNetworkState(): Flow<Result<Boolean>> = holder.client.map {
        try {
            it.getOrThrow()
            Result.success(true)
        } catch (exception: LoadingPlaceholderException) {
            Result.success(false)
        } catch (exception: RuntimeException) {
            Result.failure(exception)
        }
    }
}