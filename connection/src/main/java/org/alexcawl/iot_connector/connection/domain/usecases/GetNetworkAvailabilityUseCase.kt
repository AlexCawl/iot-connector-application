package org.alexcawl.iot_connector.connection.domain.usecases

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.util.Product
import javax.inject.Inject

class GetNetworkAvailabilityUseCase @Inject constructor(
    private val client: StateFlow<@JvmSuppressWildcards Product<Mqtt5AsyncClient>>
) {
    operator fun invoke(): Flow<Result<Boolean>> = client.map {
        when (it) {
            is Product.Empty -> Result.success(false)
            is Product.Failure -> Result.failure(it.throwable)
            is Product.Success -> Result.success(true)
        }
    }
}