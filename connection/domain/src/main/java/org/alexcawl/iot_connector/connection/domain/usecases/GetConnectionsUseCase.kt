package org.alexcawl.iot_connector.connection.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.connection.data.IConnectionRepository
import org.alexcawl.iot_connector.connection.domain.mapper.IConnectionStateMapper
import org.alexcawl.iot_connector.ui.state.ConnectionState
import javax.inject.Inject

class GetConnectionsUseCase @Inject constructor(
    private val repository: IConnectionRepository,
    private val mapper: IConnectionStateMapper
) {
    operator fun invoke(): Flow<List<ConnectionState>> =
        repository.getConnectionsAsFlow().map { it.map(mapper::mapSecond) }
}