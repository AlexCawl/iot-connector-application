package org.alexcawl.iot_connector.common.util

import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.common.model.ProfileValidationException
import kotlin.jvm.Throws

interface IProfileValidator {
    @Throws(ProfileValidationException::class)
    fun validate(
        name: String, info: String?, host: String, port: String, login: String?, password: String?
    ): Profile
}