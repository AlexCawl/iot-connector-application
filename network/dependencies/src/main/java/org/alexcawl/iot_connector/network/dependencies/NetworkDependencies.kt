package org.alexcawl.iot_connector.network.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao

interface NetworkDependencies {
    val profileDatabaseDao: ProfileDatabaseDao
    val profileDatastoreDao: ProfileDatastoreDao
    val viewModelFactory: ViewModelProvider.Factory
}