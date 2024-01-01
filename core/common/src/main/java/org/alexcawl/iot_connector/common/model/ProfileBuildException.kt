package org.alexcawl.iot_connector.common.model

sealed class ProfileBuildException : RuntimeException() {
    data object ProfileNameException : ProfileBuildException()

    data object ConfigurationHostIsEmpty : ProfileBuildException()

    data object ConfigurationPortIsEmpty : ProfileBuildException()

    data object ConfigurationPortIsNotNumber : ProfileBuildException()
}