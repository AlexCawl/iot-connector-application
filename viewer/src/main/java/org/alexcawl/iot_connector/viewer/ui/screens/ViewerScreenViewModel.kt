package org.alexcawl.iot_connector.viewer.ui.screens

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.ui.state.ViewerState
import org.alexcawl.iot_connector.ui.util.StateViewModel
import org.alexcawl.iot_connector.viewer.domain.usecases.ConnectToMqttById
import java.util.UUID
import javax.inject.Inject

class ViewerScreenViewModel @Inject constructor(
    private val connect: ConnectToMqttById
) : StateViewModel<ViewerScreenState, ViewerScreenAction>() {
    private val _id: MutableStateFlow<UUID?> = MutableStateFlow(null)

    private val _state: MutableStateFlow<ViewerScreenState> =
        MutableStateFlow(ViewerScreenState.Initial)

    override val state: StateFlow<ViewerScreenState> = _state.asStateFlow()

    override fun handle(action: ViewerScreenAction) = Unit

    suspend fun setConnectionId(uuid: UUID) = _id.emit(uuid)

    init {
        viewModelScope.launch {
            _id.collect { uuid: UUID? ->
                when (uuid) {
                    null -> Unit
                    else -> connect(uuid).collect {
                        try {
                            _state.emit(it.getOrThrow().asState())
                        } catch (exception: RuntimeException) {
                            _state.emit(ViewerScreenState.Failure(exception))
                        }
                    }
                }
            }
        }
    }

    private companion object {
        fun ViewerState.asState(): ViewerScreenState = ViewerScreenState.Viewer(
            id = id,
            endpoint = endpoint,
            name = name,
            representations = viewsData
        )
    }
}