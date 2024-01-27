package org.alexcawl.iot_connector.common.mappers

import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.common.util.OneWayMapper

interface IMqttResponseMapper <T> : OneWayMapper<MqttResponse, T>