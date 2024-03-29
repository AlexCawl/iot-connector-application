package org.alexcawl.iot_connector.profile

import org.alexcawl.iot_connector.di.ComponentStore
import org.alexcawl.iot_connector.profile.di.DaggerProfileComponent
import org.alexcawl.iot_connector.profile.di.ProfileComponent
import org.alexcawl.iot_connector.profile.di.ProfileDependencies

object ProfileComponentStore : ComponentStore<ProfileComponent, ProfileDependencies> {
    override lateinit var dependencies: ProfileDependencies

    override val component: ProfileComponent
        get() = DaggerProfileComponent.builder()
            .dependencies(dependencies)
            .build()
}