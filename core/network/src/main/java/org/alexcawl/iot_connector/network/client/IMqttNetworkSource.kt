package org.alexcawl.iot_connector.network.client

import kotlinx.coroutines.flow.Flow

interface IMqttNetworkSource <M> {
    val networkExceptions: Flow<Throwable?>

    fun subscribe(endpoint: String): Flow<Result<M>>
}