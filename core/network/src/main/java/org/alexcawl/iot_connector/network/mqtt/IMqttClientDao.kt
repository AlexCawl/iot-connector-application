package org.alexcawl.iot_connector.network.mqtt

import kotlinx.coroutines.flow.Flow

interface IMqttClientDao <T> {
    suspend fun publish(endpoint: String, publish: T)

    fun subscribe(endpoint: String): Flow<Result<T>>
}