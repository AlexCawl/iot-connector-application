package org.alexcawl.iot_connector.network.dto

import org.alexcawl.iot_connector.common.model.MqttResponsePayload

data class TextResponse(
    override val raw: String
) : MqttResponsePayload