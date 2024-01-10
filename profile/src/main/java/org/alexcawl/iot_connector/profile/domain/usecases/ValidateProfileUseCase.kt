package org.alexcawl.iot_connector.profile.domain.usecases

import org.alexcawl.iot_connector.ui.state.ProfileState
import java.util.UUID
import javax.inject.Inject
import kotlin.jvm.Throws

class ValidateProfileUseCase @Inject constructor() {
    @Throws(ProfileValidationException::class)
    operator fun invoke(
        name: String, info: String?, host: String, port: String, login: String?, password: String?
    ): ProfileState  {
        val validName: String = when (name.isBlank()) {
            true -> throw ProfileValidationException.ProfileNameIsEmpty
            false -> name
        }
        val validHost: String = when (host.isBlank()) {
            true -> throw throw ProfileValidationException.ConfigurationHostIsEmpty
            false -> host
        }
        val validPort: Int = when (port.isBlank()) {
            true -> throw throw ProfileValidationException.ConfigurationPortIsEmpty
            false -> try {
                port.toInt()
            } catch (exception: NumberFormatException) {
                throw ProfileValidationException.ConfigurationPortIsNotNumber
            }
        }

        return ProfileState(
            id = UUID.randomUUID(),
            name = validName,
            createdAt = System.currentTimeMillis(),
            host = validHost,
            port = validPort,
            login = login,
            password = password,
            info = info,
            changedAt = null
        )
    }
}

sealed class ProfileValidationException : RuntimeException() {
    data object ProfileNameIsEmpty : ProfileValidationException()

    data object ConfigurationHostIsEmpty : ProfileValidationException()

    data object ConfigurationPortIsEmpty : ProfileValidationException()

    data object ConfigurationPortIsNotNumber : ProfileValidationException()
}