package org.alexcawl.iot_connector.connection.data

import kotlinx.coroutines.flow.Flow

interface INetworkStateRepository {
    fun getNetworkState() : Flow<Result<Boolean>>
}