package org.alexcawl.iot_connector.connection.data

import kotlinx.coroutines.flow.Flow
import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.connection.domain.IConnectionRepository
import java.util.UUID

class ConnectionRepository : IConnectionRepository {
    override fun getConnectionsAsFlow(): Flow<List<ConnectionModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun createConnection(model: ConnectionModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getConnection(id: UUID): ConnectionModel {
        TODO("Not yet implemented")
    }

    override suspend fun updateConnection(model: ConnectionModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteConnection(id: UUID) {
        TODO("Not yet implemented")
    }
}