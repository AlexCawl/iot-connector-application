package org.alexcawl.iot_connector.connection.domain

import kotlinx.coroutines.flow.Flow

interface IMqttNetworkStatusRepository {
    fun getNetworkStatus(): Flow<Boolean>
}