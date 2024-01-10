package org.alexcawl.iot_connector.persistence.mappers.impl

import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.persistence.entity.ConnectionEntity
import org.alexcawl.iot_connector.persistence.mappers.IConnectionEntityMapper
import javax.inject.Inject

class ConnectionEntityMapper @Inject constructor() : IConnectionEntityMapper {
    override fun mapFirst(from: ConnectionEntity): ConnectionModel {
        TODO("Not yet implemented")
    }

    override fun mapSecond(from: ConnectionModel): ConnectionEntity {
        TODO("Not yet implemented")
    }
}