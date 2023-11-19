package org.alexcawl.iot_connector.profile.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.pref.dao.ProfileDatastoreDao

interface ProfileDependencies {
    val profileDatastoreDao: ProfileDatastoreDao
    val profileDatabaseDao: ProfileDatabaseDao
    val viewModelFactory: ViewModelProvider.Factory
}