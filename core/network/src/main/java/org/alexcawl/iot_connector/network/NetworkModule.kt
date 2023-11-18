package org.alexcawl.iot_connector.network

import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindsDataSource(dataSource: DataSource): IDataSource
}