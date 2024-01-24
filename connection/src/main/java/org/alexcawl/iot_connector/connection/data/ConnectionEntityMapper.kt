package org.alexcawl.iot_connector.connection.data

import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.connection.domain.IConnectionEntityMapper
import org.alexcawl.iot_connector.persistence.entity.ConnectionEntity
import javax.inject.Inject

class ConnectionEntityMapper @Inject constructor() : IConnectionEntityMapper {
    override fun mapFirst(from: ConnectionEntity): ConnectionModel = with(from) {
        ConnectionModel(id, endpoint, name)
    }

    override fun mapSecond(from: ConnectionModel): ConnectionEntity = with(from) {
        ConnectionEntity(id, endpoint, name)
    }
}