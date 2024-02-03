package org.alexcawl.iot_connector.viewer.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.ui.state.ViewerState
import org.alexcawl.iot_connector.viewer.data.IMqttResponseRepository
import org.alexcawl.iot_connector.viewer.domain.mapper.IViewerStateMapper
import java.util.UUID
import javax.inject.Inject

class ConnectToMqttById @Inject constructor(
    private val repository: IMqttResponseRepository,
    private val mapper: IViewerStateMapper
) {
    operator fun invoke(id: UUID): Flow<Result<ViewerState>> = repository.subscribe(id).map { it.map(mapper::map) }
}