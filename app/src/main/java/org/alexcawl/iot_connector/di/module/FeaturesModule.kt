package org.alexcawl.iot_connector.di.module

import dagger.Module
import org.alexcawl.iot_connector.connection.ConnectionsModule
import org.alexcawl.iot_connector.profile.ProfileModule
import org.alexcawl.iot_connector.title.TitleModule
import org.alexcawl.iot_connector.viewer.ViewerModule

@Module(includes = [ConnectionsModule::class, ProfileModule::class, ViewerModule::class, TitleModule::class])
interface FeaturesModule