package org.alexcawl.iot_connector.viewer.domain

import org.alexcawl.iot_connector.common.util.OneWayMapper

interface IThermalMatrixMapper : OneWayMapper<List<Int>, Result<Array<Array<Float>>>>