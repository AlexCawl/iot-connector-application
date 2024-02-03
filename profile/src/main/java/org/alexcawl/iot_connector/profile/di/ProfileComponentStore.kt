package org.alexcawl.iot_connector.profile.di

import org.alexcawl.iot_connector.di.ComponentStore
import kotlin.properties.Delegates

object ProfileComponentStore : ComponentStore<ProfileComponent, ProfileDependencies> {
    override var component: ProfileComponent by Delegates.notNull()

    override fun inject(dependencies: ProfileDependencies) {
        component = DaggerProfileComponent.builder()
            .dependencies(dependencies)
            .build()
    }
}