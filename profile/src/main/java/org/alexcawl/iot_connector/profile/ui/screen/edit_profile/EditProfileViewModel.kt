package org.alexcawl.iot_connector.profile.ui.screen.edit_profile

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.ui.util.StateViewModel
import java.util.UUID
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val source: IProfileService
) : StateViewModel<EditProfileScreenState, EditProfileScreenAction>() {
    private val _state: MutableStateFlow<EditProfileScreenState> = MutableStateFlow(EditProfileScreenState.Initial)
    override val state: StateFlow<EditProfileScreenState> = _state.asStateFlow()

    private val _profileId: MutableStateFlow<UUID?> = MutableStateFlow(null)

    override fun handle(action: EditProfileScreenAction) = Unit

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            _profileId.collect { id ->
                when (id) {
                    null -> Unit
                    else -> source.getProfile(id).collect { profile ->
                        when (profile) {
                            null -> _state.emit(EditProfileScreenState.ProfileNotFound)
                            else -> _state.emit(EditProfileScreenState.Successful(profile))
                        }
                    }
                }
            }
        }
    }
}