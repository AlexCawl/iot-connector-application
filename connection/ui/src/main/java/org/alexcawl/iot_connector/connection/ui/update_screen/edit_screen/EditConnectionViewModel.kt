package org.alexcawl.iot_connector.connection.ui.update_screen.edit_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.connection.domain.usecases.ConnectionValidationException
import org.alexcawl.iot_connector.connection.domain.usecases.DeleteConnectionByIdUseCase
import org.alexcawl.iot_connector.connection.domain.usecases.GetConnectionByIdUseCase
import org.alexcawl.iot_connector.connection.domain.usecases.UpdateConnectionUseCase
import org.alexcawl.iot_connector.connection.domain.usecases.ValidateConnectionUseCase
import org.alexcawl.iot_connector.connection.ui.update_screen.UpdateConnectionScreenAction
import org.alexcawl.iot_connector.connection.ui.update_screen.UpdateConnectionScreenState
import org.alexcawl.iot_connector.connection.ui.update_screen.UpdateConnectionViewModel
import org.alexcawl.iot_connector.ui.state.ConnectionState
import java.util.UUID
import javax.inject.Inject

class EditConnectionViewModel @Inject constructor(
    private val getConnection: GetConnectionByIdUseCase,
    private val updateConnection: UpdateConnectionUseCase,
    private val deleteConnection: DeleteConnectionByIdUseCase,
    private val validateConnection: ValidateConnectionUseCase
) : UpdateConnectionViewModel() {
    private val _connectionId: MutableStateFlow<UUID?> = MutableStateFlow(null)

    suspend fun setConnectionId(uuid: UUID) = _connectionId.emit(uuid)

    init {
        viewModelScope.launch {
            _connectionId.collect { id ->
                when (id) {
                    null -> Unit
                    else -> when (val connection = getConnection(id)) {
                        null -> _state.emit(NotFound)
                        else -> _state.emit(connection.asScreenState())
                    }
                }
            }
        }
    }

    override fun handle(action: UpdateConnectionScreenAction) {
        viewModelScope.launch {
            when (action) {
                Delete -> _connectionId.collect { connectionId ->
                    when (connectionId) {
                        null -> _state.emit(NotFound)
                        else -> {
                            deleteConnection(connectionId)
                            _state.emit(UpdateConnectionScreenState.Saving)
                        }
                    }
                }
                else -> super.handle(action)
            }
        }
    }

    override suspend fun saveConnection(screenState: UpdateConnectionScreenState.Builder) = try {
        val connection = validateConnection(
            endpoint = screenState.endpoint,
            name = if (screenState.nameOptional) null else screenState.name
        )
        _connectionId.collect { profileId ->
            when (profileId) {
                null -> _state.emit(NotFound)
                else -> {
                    updateConnection(profileId, connection)
                    _state.emit(UpdateConnectionScreenState.Saving)
                }
            }
        }
    } catch (exception: ConnectionValidationException) {
        when (exception) {
            ConnectionValidationException.EndpointIsEmpty -> _state.emit(
                screenState.copy(
                    endpointMessage = UpdateConnectionScreenState.Builder.Message.EMPTY
                )
            )

            ConnectionValidationException.EndpointIsWrongPattern -> _state.emit(
                screenState.copy(
                    endpointMessage = UpdateConnectionScreenState.Builder.Message.WRONG_PATTERN
                )
            )
        }
    }

    private companion object {
        fun ConnectionState.asScreenState(): UpdateConnectionScreenState.Builder =
            UpdateConnectionScreenState.Builder(
                endpoint = endpoint,
                name = name ?: "",
                nameOptional = name == null
            )
    }
}