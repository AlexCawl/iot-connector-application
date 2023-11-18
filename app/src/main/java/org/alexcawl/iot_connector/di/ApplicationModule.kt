package org.alexcawl.iot_connector.di

import dagger.Module
import org.alexcawl.iot_connector.di.module.FeaturesModule
import org.alexcawl.iot_connector.di.module.NetworkModule
import org.alexcawl.iot_connector.di.module.PersistenceModule
import org.alexcawl.iot_connector.di.module.ViewModelModule

@Module(
    includes = [ViewModelModule::class, NetworkModule::class, PersistenceModule::class, FeaturesModule::class]
)
interface ApplicationModule