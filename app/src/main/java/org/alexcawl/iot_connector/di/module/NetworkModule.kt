package org.alexcawl.iot_connector.di.module

import dagger.Binds
import dagger.Module
import org.alexcawl.iot_connector.network.DataSource
import org.alexcawl.iot_connector.network.IDataSource

@Module
interface NetworkModule {
    @Binds
    fun bindsDataSource(dataSource: DataSource): IDataSource
}