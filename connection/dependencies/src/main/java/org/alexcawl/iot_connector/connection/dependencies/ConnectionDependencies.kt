package org.alexcawl.iot_connector.connection.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.db.dao.ConnectionDatabaseDao
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.pref.dao.ProfileDatastoreDao

interface ConnectionDependencies {
    val profileDatastoreDao: ProfileDatastoreDao
    val profileDatabaseDao: ProfileDatabaseDao
    val connectionDatabaseDao: ConnectionDatabaseDao
    val viewModelFactory: ViewModelProvider.Factory
}