package org.alexcawl.iot_connector.profile.dependencies

import androidx.lifecycle.ViewModelProvider
import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import org.alexcawl.iot_connector.ui.mappers.IProfileStateMapper

interface ProfileDependencies {
    val profileRepository: IProfileRepository
    val profileStateMapper: IProfileStateMapper
    val viewModelFactory: ViewModelProvider.Factory
}