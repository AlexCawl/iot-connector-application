package org.alexcawl.iot_connector.connection.ui.screen.update

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.ui.util.StateViewModel

abstract class UpdateConnectionViewModel : StateViewModel<UpdateConnectionScreenState, UpdateConnectionScreenAction>() {
    protected val _state: MutableStateFlow<UpdateConnectionScreenState> =
        MutableStateFlow(UpdateConnectionScreenState.Initial)
    override val state: StateFlow<UpdateConnectionScreenState> = _state.asStateFlow()

    override fun handle(action: UpdateConnectionScreenAction) {
        viewModelScope.launch {
            when (action) {
                is UpdateConnectionScreenAction.SetEndpoint -> when (val screenState = state.first()) {
                    is UpdateConnectionScreenState.Builder -> _state.emit(screenState.copy(endpoint = action.endpoint))
                    else -> throw IllegalStateException()
                }

                is UpdateConnectionScreenAction.SetName -> when (val screenState = state.first()) {
                    is UpdateConnectionScreenState.Builder -> _state.emit(screenState.copy(name = action.name))
                    else -> throw IllegalStateException()
                }

                is UpdateConnectionScreenAction.SetNameType -> when (val screenState = state.first()) {
                    is UpdateConnectionScreenState.Builder -> _state.emit(screenState.copy(nameOptional = action.optional))
                    else -> throw IllegalStateException()
                }

                is UpdateConnectionScreenAction.Save -> when (val screenState = state.first()) {
                    is UpdateConnectionScreenState.Builder -> saveConnection(screenState)
                    else -> throw IllegalStateException()
                }

                else -> throw IllegalStateException()
            }
        }
    }

    protected abstract suspend fun saveConnection(screenState: UpdateConnectionScreenState.Builder)
}