package org.alexcawl.iot_connector.connections.dependencies

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.network.IDataSource

interface ConnectionDependencies {
    val context: Context
    val dataSource: IDataSource
    val viewModelFactory: ViewModelProvider.Factory
}