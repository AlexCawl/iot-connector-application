package org.alexcawl.iot_connector.connection.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.network.IDataSource

interface ConnectionDependencies {
    val dataSource: IDataSource
    val viewModelFactory: ViewModelProvider.Factory
}