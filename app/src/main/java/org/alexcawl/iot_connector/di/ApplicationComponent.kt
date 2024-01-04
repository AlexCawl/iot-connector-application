package org.alexcawl.iot_connector.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.alexcawl.iot_connector.IoTConnectorApplication
import org.alexcawl.iot_connector.MainActivity
import org.alexcawl.iot_connector.connection.dependencies.ConnectionDependencies
import org.alexcawl.iot_connector.profile.dependencies.ProfileDependencies

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : ConnectionDependencies, ProfileDependencies {
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: IoTConnectorApplication)

    fun inject(activity: MainActivity)
}