package org.alexcawl.iot_connector

import android.app.Application
import org.alexcawl.iot_connector.connection.dependencies.ConnectionDependenciesStore
import org.alexcawl.iot_connector.di.ApplicationComponent
import org.alexcawl.iot_connector.di.DaggerApplicationComponent
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependenciesStore
import org.alexcawl.iot_connector.viewer.dependencies.ViewerDependenciesStore

class IoTConnectorApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.apply {
            // initialize feature dependencies here
            ConnectionDependenciesStore.dependencies = this
            ProfileDependenciesStore.dependencies = this
            ViewerDependenciesStore.dependencies = this
        }
    }
}