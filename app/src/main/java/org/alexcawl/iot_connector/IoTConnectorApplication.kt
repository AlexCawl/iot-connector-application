package org.alexcawl.iot_connector

import android.app.Application
import org.alexcawl.iot_connector.connections.dependencies.ConnectionDependenciesStore
import org.alexcawl.iot_connector.di.ApplicationComponent
import org.alexcawl.iot_connector.di.DaggerApplicationComponent
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependenciesStore

class IoTConnectorApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.apply {
            ConnectionDependenciesStore.dependencies = this
            ProfileDependenciesStore.dependencies = this
        }
        // initialize feature deps here
    }
}