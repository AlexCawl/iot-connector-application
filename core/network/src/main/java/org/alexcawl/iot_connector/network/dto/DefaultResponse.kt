package org.alexcawl.iot_connector.network.dto

data class DefaultResponse(
    val payload: String
) : MqttNetworkResponse
