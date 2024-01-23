package org.alexcawl.iot_connector.connection.data

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.util.Product
import org.alexcawl.iot_connector.connection.domain.IMqttNetworkStatusRepository
import javax.inject.Inject

class MqttNetworkStatusRepository @Inject constructor(
    private val client: StateFlow<Product<Mqtt5AsyncClient>>
): IMqttNetworkStatusRepository {
    override fun getNetworkStatus(): Flow<Boolean> = client.map {
        when(it) {
            is Product.Success -> true
            else -> false
        }
    }
}