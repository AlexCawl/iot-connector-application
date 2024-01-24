package org.alexcawl.iot_connector.connection.data

import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.connection.domain.IConnectionStateMapper
import org.alexcawl.iot_connector.ui.state.ConnectionState

class ConnectionStateMapper : IConnectionStateMapper {
    override fun mapFirst(from: ConnectionState): ConnectionModel = with(from) {
        ConnectionModel(id, endpoint, name)
    }

    override fun mapSecond(from: ConnectionModel): ConnectionState = with(from) {
        ConnectionState(id, endpoint, name)
    }
}