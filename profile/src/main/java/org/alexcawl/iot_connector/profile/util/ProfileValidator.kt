package org.alexcawl.iot_connector.profile.util

import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.common.model.ProfileValidationException
import org.alexcawl.iot_connector.common.util.IProfileValidator
import java.util.UUID
import javax.inject.Inject
import kotlin.jvm.Throws

class ProfileValidator @Inject constructor() : IProfileValidator {
    @Throws(ProfileValidationException::class)
    override fun validate(
        name: String, info: String?, host: String, port: String, login: String?, password: String?
    ): Profile {
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

        return Profile(
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