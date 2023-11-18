package org.alexcawl.iot_connector

import android.app.Application
import org.alexcawl.iot_connector.connections.dependencies.ConnectionDependenciesStore
import org.alexcawl.iot_connector.di.ApplicationComponent
import org.alexcawl.iot_connector.di.DaggerApplicationComponent

class IoTConnectorApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        ConnectionDependenciesStore.dependencies = applicationComponent
        // initialize feature deps here
    }
}