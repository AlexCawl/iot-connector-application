package org.alexcawl.iot_connector.connection

import dagger.Module
import org.alexcawl.iot_connector.connection.data.ConnectionEntityMapper
import org.alexcawl.iot_connector.connection.data.ConnectionRepository
import org.alexcawl.iot_connector.connection.data.ConnectionStateMapper
import org.alexcawl.iot_connector.connection.domain.IConnectionEntityMapper
import org.alexcawl.iot_connector.connection.domain.IConnectionRepository
import org.alexcawl.iot_connector.connection.domain.IConnectionStateMapper

@Module
interface ConnectionsModule {
    fun bindConnectionEntityMapper(mapper: ConnectionEntityMapper): IConnectionEntityMapper

    fun bindConnectionStateMapper(mapper: ConnectionStateMapper): IConnectionStateMapper

    fun bindConnectionRepository(repository: ConnectionRepository): IConnectionRepository
}