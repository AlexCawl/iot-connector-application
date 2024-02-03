package org.alexcawl.iot_connector.connection.domain.usecases

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.connection.data.INetworkStateRepository
import javax.inject.Inject

class GetNetworkAvailabilityUseCase @Inject constructor(
    private val repository: INetworkStateRepository
) {
    operator fun invoke(): Flow<Result<Boolean>> = repository.getNetworkState()
}