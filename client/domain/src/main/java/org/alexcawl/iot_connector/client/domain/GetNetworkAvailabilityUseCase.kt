package org.alexcawl.iot_connector.client.domain

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.client.data.INetworkStateRepository
import javax.inject.Inject

class GetNetworkAvailabilityUseCase @Inject constructor(
    private val repository: INetworkStateRepository
) {
    operator fun invoke(): Flow<Result<Boolean>> = repository.getNetworkState()
}