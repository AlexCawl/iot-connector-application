package org.alexcawl.iot_connector.connection.domain.usecases

import org.alexcawl.iot_connector.connection.data.IConnectionRepository
import org.alexcawl.iot_connector.connection.domain.mapper.IConnectionStateMapper
import java.util.UUID
import javax.inject.Inject

class GetConnectionByIdUseCase @Inject constructor(
    private val repository: IConnectionRepository,
    private val mapper: IConnectionStateMapper
) {
    suspend operator fun invoke(id: UUID) = repository.getConnection(id)?.let(mapper::mapSecond)
}