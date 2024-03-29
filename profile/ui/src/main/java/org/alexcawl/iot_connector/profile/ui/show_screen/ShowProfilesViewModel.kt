package org.alexcawl.iot_connector.profile.ui.show_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.profile.domain.usecase.GetProfilesUseCase
import org.alexcawl.iot_connector.profile.domain.usecase.UpdateSelectedProfileIdUseCase
import org.alexcawl.iot_connector.ui.state.ProfileState
import org.alexcawl.iot_connector.ui.util.StateViewModel
import javax.inject.Inject

class ShowProfilesViewModel @Inject constructor(
    private val getProfiles: GetProfilesUseCase,
    private val updateSelectedID: UpdateSelectedProfileIdUseCase
) : StateViewModel<ShowProfilesScreenState, ShowProfilesScreenAction>() {
    private val _state: MutableStateFlow<ShowProfilesScreenState> =
        MutableStateFlow(ShowProfilesScreenState.Initial)
    override val state: StateFlow<ShowProfilesScreenState> = _state.asStateFlow()

    override fun handle(action: ShowProfilesScreenAction) {
        viewModelScope.launch {
            when (action) {
                is ShowProfilesScreenAction.SelectProfileById -> when (val current = _state.value) {
                    is ShowProfilesScreenState.Initial -> throw IllegalStateException()
                    is ShowProfilesScreenState.Viewing -> updateSelectedID(
                        current.selectedProfile?.id,
                        action.id
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            getProfiles().collect { (selectedProfile: ProfileState?, profiles: List<ProfileState>) ->
                _state.emit(ShowProfilesScreenState.Viewing(selectedProfile, profiles))
            }
        }
    }
}