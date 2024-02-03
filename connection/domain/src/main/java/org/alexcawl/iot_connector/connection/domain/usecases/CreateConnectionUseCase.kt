package org.alexcawl.iot_connector.connection.domain.usecases

import org.alexcawl.iot_connector.connection.data.IConnectionRepository
import org.alexcawl.iot_connector.connection.domain.mapper.IConnectionStateMapper
import org.alexcawl.iot_connector.ui.state.ConnectionState
import javax.inject.Inject

class CreateConnectionUseCase @Inject constructor(
    private val repository: IConnectionRepository,
    private val mapper: IConnectionStateMapper
) {
    suspend operator fun invoke(connection: ConnectionState) =
        repository.createConnection(mapper.mapFirst(connection))
}