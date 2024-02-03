package org.alexcawl.iot_connector.connection.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao
import java.util.UUID
import javax.inject.Inject

class ConnectionRepository @Inject constructor(
    private val databaseDao: ConnectionDatabaseDao,
    private val mapper: IConnectionEntityMapper
) : IConnectionRepository {
    override fun getConnectionsAsFlow(): Flow<List<ConnectionModel>> =
        databaseDao.subscribeAtConnections().map { it.map(mapper::mapFirst) }

    override suspend fun createConnection(model: ConnectionModel) =
        databaseDao.createConnection(mapper.mapSecond(model))

    override suspend fun getConnection(id: UUID): ConnectionModel? =
        when (val selectedEntity = databaseDao.getConnection(id)) {
            null -> null
            else -> mapper.mapFirst(selectedEntity)
        }

    override suspend fun updateConnection(model: ConnectionModel) =
        databaseDao.updateConnection(mapper.mapSecond(model))

    override suspend fun deleteConnection(id: UUID) =
        when (val deletedEntity = databaseDao.getConnection(id)) {
            null -> Unit
            else -> databaseDao.deleteConnection(deletedEntity)
        }
}