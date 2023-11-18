package org.alexcawl.iot_connector.connections.data

import org.alexcawl.iot_connector.network.IDataSource
import javax.inject.Inject

class ConnectionService @Inject constructor(
    private val dataSource: IDataSource
) {

}