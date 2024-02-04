package org.alexcawl.iot_connector

import android.app.Application
import org.alexcawl.iot_connector.client.ClientComponentStore
import org.alexcawl.iot_connector.connection.ConnectionComponentStore
import org.alexcawl.iot_connector.di.ApplicationComponent
import org.alexcawl.iot_connector.di.DaggerApplicationComponent
import org.alexcawl.iot_connector.profile.ProfileComponentStore
import org.alexcawl.iot_connector.viewer.ViewerComponentStore

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
            ProfileComponentStore.inject(this)
            ConnectionComponentStore.inject(this)
            ViewerComponentStore.inject(this)
            ClientComponentStore.inject(this)
        }
    }
}