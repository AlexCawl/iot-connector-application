package org.alexcawl.iot_connector.connection.data

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.ConnectionModel
import java.util.UUID

interface IConnectionRepository {
    fun getConnectionsAsFlow(): Flow<List<ConnectionModel>>

    suspend fun createConnection(model: ConnectionModel)

    suspend fun getConnection(id: UUID): ConnectionModel?

    suspend fun updateConnection(model: ConnectionModel)

    suspend fun deleteConnection(id: UUID)
}