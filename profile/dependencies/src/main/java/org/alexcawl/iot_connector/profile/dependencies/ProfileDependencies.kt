package org.alexcawl.iot_connector.profile.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao

interface ProfileDependencies {
    val profileDatabaseDao: ProfileDatabaseDao
    val profileDatastoreDao: ProfileDatastoreDao
    val viewModelFactory: ViewModelProvider.Factory
}