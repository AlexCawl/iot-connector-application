package org.alexcawl.iot_connector.viewer.domain.mapper

import org.alexcawl.iot_connector.common.mappers.IMqttResponseMapper
import org.alexcawl.iot_connector.ui.state.ViewerState

interface IViewerStateMapper : IMqttResponseMapper<ViewerState>