package org.alexcawl.iot_connector.profile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.persistence.repository.IProfileRepository
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper
import org.alexcawl.iot_connector.ui.data.ProfileState
import javax.inject.Inject

class GetSelectedProfileUseCase @Inject constructor(
    private val repository: IProfileRepository,
    private val mapper: IProfileStateMapper
) {
    operator fun invoke(): Flow<ProfileState?> =
        repository.getSelectedProfileAsFlow().map { selectedProfile ->
            selectedProfile?.let {
                mapper.mapSecond(selectedProfile)
            }
        }
}