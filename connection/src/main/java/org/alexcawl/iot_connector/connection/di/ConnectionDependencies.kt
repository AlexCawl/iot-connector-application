package org.alexcawl.iot_connector.connection.di

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao

interface ConnectionDependencies {
    val connectionDatabaseDao: ConnectionDatabaseDao
    val viewModelFactory: ViewModelProvider.Factory
}