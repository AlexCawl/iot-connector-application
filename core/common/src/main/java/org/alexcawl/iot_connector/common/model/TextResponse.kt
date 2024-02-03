package org.alexcawl.iot_connector.common.model


data class TextResponse(
    override val raw: String
) : MqttResponsePayload