package org.alexcawl.iot_connector.network

import kotlinx.coroutines.flow.Flow

interface IDataSource {
    fun getData(): Flow<String>
}