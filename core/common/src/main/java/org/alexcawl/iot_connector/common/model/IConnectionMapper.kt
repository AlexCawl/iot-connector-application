package org.alexcawl.iot_connector.common.model

import org.alexcawl.iot_connector.common.util.TwoWayMapper

interface IConnectionMapper <T> : TwoWayMapper<T, ConnectionModel>