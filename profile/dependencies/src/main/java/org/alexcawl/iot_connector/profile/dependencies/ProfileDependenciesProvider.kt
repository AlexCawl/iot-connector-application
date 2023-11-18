package org.alexcawl.iot_connector.profile.dependencies

interface ProfileDependenciesProvider {
    val dependencies: ProfileDependencies

    companion object : ProfileDependenciesProvider by ProfileDependenciesStore
}