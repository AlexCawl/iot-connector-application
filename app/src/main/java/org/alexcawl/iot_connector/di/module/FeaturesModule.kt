package org.alexcawl.iot_connector.di.module

import dagger.Module
import org.alexcawl.iot_connector.client.di.ClientModule
import org.alexcawl.iot_connector.connection.di.ConnectionsModule
import org.alexcawl.iot_connector.profile.di.ProfileModule
import org.alexcawl.iot_connector.viewer.di.ViewerModule

@Module(includes = [ConnectionsModule::class, ProfileModule::class, ViewerModule::class, ClientModule::class])
interface FeaturesModule