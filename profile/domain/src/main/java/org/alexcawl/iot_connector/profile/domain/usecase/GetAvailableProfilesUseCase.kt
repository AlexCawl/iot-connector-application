package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.profile.data.repository.IProfileRepository
import org.alexcawl.iot_connector.profile.domain.mapper.IProfileStateMapper
import org.alexcawl.iot_connector.ui.state.ProfileState
import javax.inject.Inject

class GetAvailableProfilesUseCase @Inject constructor(
    private val repository: IProfileRepository,
    private val mapper: IProfileStateMapper
) {
    operator fun invoke(): Flow<List<ProfileState>> = with(mapper) {
        repository.getProfilesAsFlow().map { profiles -> profiles.map(::mapSecond) }
    }
}