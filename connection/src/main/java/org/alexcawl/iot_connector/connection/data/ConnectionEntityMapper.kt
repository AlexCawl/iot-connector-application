package org.alexcawl.iot_connector.connection.data

import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.connection.domain.IConnectionEntityMapper
import org.alexcawl.iot_connector.persistence.entity.ConnectionEntity

class ConnectionEntityMapper : IConnectionEntityMapper {
    override fun mapFirst(from: ConnectionEntity): ConnectionModel {
        TODO("Not yet implemented")
    }

    override fun mapSecond(from: ConnectionModel): ConnectionEntity {
        TODO("Not yet implemented")
    }
}