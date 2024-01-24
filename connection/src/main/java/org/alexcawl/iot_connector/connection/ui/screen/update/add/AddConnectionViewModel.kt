package org.alexcawl.iot_connector.connection.ui.screen.update.add

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.connection.domain.usecases.ConnectionValidationException
import org.alexcawl.iot_connector.connection.domain.usecases.CreateConnectionUseCase
import org.alexcawl.iot_connector.connection.domain.usecases.ValidateConnectionUseCase
import org.alexcawl.iot_connector.connection.ui.screen.update.UpdateConnectionScreenState
import org.alexcawl.iot_connector.connection.ui.screen.update.UpdateConnectionViewModel
import javax.inject.Inject

class AddConnectionViewModel @Inject constructor(
    private val createConnection: CreateConnectionUseCase,
    private val validateConnection: ValidateConnectionUseCase
) : UpdateConnectionViewModel() {
    init {
        viewModelScope.launch {
            _state.emit(UpdateConnectionScreenState.Builder())
        }
    }

    override suspend fun saveConnection(screenState: UpdateConnectionScreenState.Builder) = try {
        val connection = validateConnection(
            endpoint = screenState.endpoint,
            name = if (screenState.nameOptional) null else screenState.name
        )
        createConnection(connection)
        _state.emit(UpdateConnectionScreenState.Saving)
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
}