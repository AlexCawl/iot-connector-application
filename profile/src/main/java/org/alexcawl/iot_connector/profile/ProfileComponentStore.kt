package org.alexcawl.iot_connector.profile

import org.alexcawl.iot_connector.di.ComponentStore
import org.alexcawl.iot_connector.profile.di.DaggerProfileComponent
import org.alexcawl.iot_connector.profile.di.ProfileComponent
import org.alexcawl.iot_connector.profile.di.ProfileDependencies
import kotlin.properties.Delegates

object ProfileComponentStore : ComponentStore<ProfileComponent, ProfileDependencies> {
    override var component: ProfileComponent by Delegates.notNull()

    override fun inject(dependencies: ProfileDependencies) {
        component = DaggerProfileComponent.builder()
            .dependencies(dependencies)
            .build()
    }
}