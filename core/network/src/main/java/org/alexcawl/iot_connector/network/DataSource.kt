package org.alexcawl.iot_connector.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataSource @Inject constructor() : IDataSource {
    override fun getData(): Flow<String> = flow {
        emit("Hello")
        delay(5000)
        emit("world!")
    }
}