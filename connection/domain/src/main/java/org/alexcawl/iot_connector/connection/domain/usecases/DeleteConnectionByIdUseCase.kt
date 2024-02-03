package org.alexcawl.iot_connector.connection.domain.usecases

import org.alexcawl.iot_connector.connection.data.IConnectionRepository
import java.util.UUID
import javax.inject.Inject

class DeleteConnectionByIdUseCase @Inject constructor(
    private val repository: IConnectionRepository
) {
    suspend operator fun invoke(id: UUID) = repository.deleteConnection(id)
}