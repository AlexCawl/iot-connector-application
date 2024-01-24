package org.alexcawl.iot_connector.connection.ui.screen.show

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.connection.domain.usecases.GetConnectionsUseCase
import org.alexcawl.iot_connector.connection.domain.usecases.GetNetworkAvailabilityUseCase
import org.alexcawl.iot_connector.ui.state.ConnectionState
import org.alexcawl.iot_connector.ui.util.StateViewModel
import javax.inject.Inject

class ShowConnectionsViewModel @Inject constructor(
    getConnections: GetConnectionsUseCase,
    getNetworkStatus: GetNetworkAvailabilityUseCase
) : StateViewModel<ShowConnectionsScreenState, ShowConnectionsScreenAction>() {
    private val _state: MutableStateFlow<ShowConnectionsScreenState> =
        MutableStateFlow(ShowConnectionsScreenState.Initial)

    override val state: StateFlow<ShowConnectionsScreenState> = _state.asStateFlow()

    override fun handle(action: ShowConnectionsScreenAction) = Unit

    init {
        viewModelScope.launch {
            getConnections().combine(getNetworkStatus()) { connections: List<ConnectionState>, network: Result<Boolean> ->
                ShowConnectionsScreenState.Viewer(connections, network)
            }.collect {
                _state.emit(it)
            }
        }
    }
}