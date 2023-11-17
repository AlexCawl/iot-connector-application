package org.alexcawl.iot_connector.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.alexcawl.iot_connector.IoTConnectorApplication
import org.alexcawl.iot_connector.MainActivity

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: IoTConnectorApplication)

    fun inject(activity: MainActivity)
}