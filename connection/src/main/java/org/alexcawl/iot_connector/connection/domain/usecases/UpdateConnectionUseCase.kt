package org.alexcawl.iot_connector.connection.domain.usecases

import org.alexcawl.iot_connector.connection.domain.IConnectionRepository
import org.alexcawl.iot_connector.ui.state.ConnectionState
import java.util.UUID
import javax.inject.Inject

class UpdateConnectionUseCase @Inject constructor(
    private val repository: IConnectionRepository,
    private val create: CreateConnectionUseCase
) {
    suspend operator fun invoke(connectionID: UUID, data: ConnectionState) =
        when (val updatedConnection = repository.getConnection(connectionID)) {
            null -> create(data)
            else -> repository.updateConnection(
                with(data) {
                    updatedConnection.copy(
                        endpoint = endpoint, name = name,
                    )
                }
            )
        }
}