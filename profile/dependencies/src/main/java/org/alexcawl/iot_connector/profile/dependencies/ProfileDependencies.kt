package org.alexcawl.iot_connector.profile.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao

interface ProfileDependencies {
    val profileDatastoreDao: ProfileDatastoreDao
    val profileDatabaseDao: ProfileDatabaseDao
    val viewModelFactory: ViewModelProvider.Factory
}