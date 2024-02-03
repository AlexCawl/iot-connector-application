package org.alexcawl.iot_connector.connection.domain.mapper

import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.ui.state.ConnectionState
import javax.inject.Inject

class ConnectionStateMapper @Inject constructor() : IConnectionStateMapper {
    override fun mapFirst(from: ConnectionState): ConnectionModel = with(from) {
        ConnectionModel(id, endpoint, name)
    }

    override fun mapSecond(from: ConnectionModel): ConnectionState = with(from) {
        ConnectionState(id, endpoint, name)
    }
}