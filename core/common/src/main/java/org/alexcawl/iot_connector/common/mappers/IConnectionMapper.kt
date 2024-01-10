package org.alexcawl.iot_connector.common.mappers

import org.alexcawl.iot_connector.common.model.ConnectionModel
import org.alexcawl.iot_connector.common.util.TwoWayMapper

interface IConnectionMapper <T> : TwoWayMapper<T, ConnectionModel>