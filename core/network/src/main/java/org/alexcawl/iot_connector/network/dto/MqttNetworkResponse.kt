package org.alexcawl.iot_connector.network.dto

interface MqttNetworkResponse {
    data object None : MqttNetworkResponse

    data class Exception(val cause: Throwable) : MqttNetworkResponse
}