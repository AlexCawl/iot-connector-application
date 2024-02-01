package org.alexcawl.iot_connector.title.dependencies

interface TitleDependenciesProvider {
    val dependencies: TitleDependencies

    companion object : TitleDependenciesProvider by TitleDependenciesStore
}