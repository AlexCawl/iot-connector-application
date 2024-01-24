package org.alexcawl.iot_connector.connection.domain.usecases

import org.alexcawl.iot_connector.ui.state.ConnectionState
import java.util.UUID
import javax.inject.Inject
import kotlin.jvm.Throws

class ValidateConnectionUseCase @Inject constructor() {
    @Throws(ConnectionValidationException::class)
    operator fun invoke(
        endpoint: String,
        name: String?
    ): ConnectionState {
        return ConnectionState(UUID.randomUUID(), endpoint, name)
    }
}

sealed class ConnectionValidationException : RuntimeException() {
    data object EndpointIsEmpty : ConnectionValidationException()

    data object EndpointIsWrongPattern : ConnectionValidationException()
}