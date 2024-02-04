package org.alexcawl.iot_connector.client.ui.status_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.client.domain.GetNetworkAvailabilityUseCase
import org.alexcawl.iot_connector.ui.util.StateViewModel
import javax.inject.Inject

class ClientStatusScreenViewModel @Inject constructor(
    private val getNetworkAvailability: GetNetworkAvailabilityUseCase
) : StateViewModel<ClientStatusScreenState, ClientStatusScreenAction>() {
    private val _state: MutableStateFlow<ClientStatusScreenState> = MutableStateFlow(ClientStatusScreenState.Initial)
    override val state: StateFlow<ClientStatusScreenState> = _state.asStateFlow()

    override fun handle(action: ClientStatusScreenAction) = Unit // TODO

    init {
        viewModelScope.launch {
            getNetworkAvailability().map {
                ClientStatusScreenState.Status(it, System.currentTimeMillis())
            }.collect {
                _state.emit(it)
            }
        }
    }
}