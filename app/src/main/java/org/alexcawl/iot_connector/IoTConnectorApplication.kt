package org.alexcawl.iot_connector

import android.app.Application
import org.alexcawl.iot_connector.di.ApplicationComponent
import org.alexcawl.iot_connector.di.DaggerApplicationComponent

class IoTConnectorApplication : Application() {
    private val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        // initialize feature deps here
    }
}