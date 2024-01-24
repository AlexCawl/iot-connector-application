package org.alexcawl.iot_connector.connection.ui.screen.update

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.ui.util.StateViewModel

abstract class ConnectionViewModel : StateViewModel<ConnectionScreenState, ConnectionScreenAction>() {
    protected val _state: MutableStateFlow<ConnectionScreenState> =
        MutableStateFlow(ConnectionScreenState.Initial)
    override val state: StateFlow<ConnectionScreenState> = _state.asStateFlow()

    override fun handle(action: ConnectionScreenAction) {
        viewModelScope.launch {
            when (action) {
                is ConnectionScreenAction.SetEndpoint -> when (val screenState = state.first()) {
                    is ConnectionScreenState.Builder -> _state.emit(screenState.copy(endpoint = action.endpoint))
                    else -> throw IllegalStateException()
                }

                is ConnectionScreenAction.SetName -> when (val screenState = state.first()) {
                    is ConnectionScreenState.Builder -> _state.emit(screenState.copy(name = action.name))
                    else -> throw IllegalStateException()
                }

                is ConnectionScreenAction.SetNameType -> when (val screenState = state.first()) {
                    is ConnectionScreenState.Builder -> _state.emit(screenState.copy(nameOptional = action.optional))
                    else -> throw IllegalStateException()
                }
            }
        }
    }

    protected abstract suspend fun saveConnection(screenState: ConnectionScreenState.Builder)
}