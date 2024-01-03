package org.alexcawl.iot_connector.profile.domain

sealed class ProfileValidationException : RuntimeException() {
    data object ProfileNameIsEmpty : ProfileValidationException()

    data object ConfigurationHostIsEmpty : ProfileValidationException()

    data object ConfigurationPortIsEmpty : ProfileValidationException()

    data object ConfigurationPortIsNotNumber : ProfileValidationException()
}