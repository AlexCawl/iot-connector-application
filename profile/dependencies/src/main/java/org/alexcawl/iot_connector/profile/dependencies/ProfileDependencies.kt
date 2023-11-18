package org.alexcawl.iot_connector.profile.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDao

interface ProfileDependencies {
    val profileDao: ProfileDao
    val viewModelFactory: ViewModelProvider.Factory
}