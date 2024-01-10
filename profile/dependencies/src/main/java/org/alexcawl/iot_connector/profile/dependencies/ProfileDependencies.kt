package org.alexcawl.iot_connector.profile.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.repository.IProfileRepository

interface ProfileDependencies {
    val profileRepository: IProfileRepository
    val viewModelFactory: ViewModelProvider.Factory
}