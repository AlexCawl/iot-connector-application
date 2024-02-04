package org.alexcawl.iot_connector.client.data

import kotlinx.coroutines.flow.Flow

interface INetworkStateRepository {
    fun getNetworkState() : Flow<Result<Boolean>>
}