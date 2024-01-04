package org.alexcawl.iot_connector.di.module

import dagger.Module
import org.alexcawl.iot_connector.connection.ConnectionsModule
import org.alexcawl.iot_connector.profile.ProfileModule

@Module(includes = [ConnectionsModule::class, ProfileModule::class])
interface FeaturesModule