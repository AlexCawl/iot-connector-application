package org.alexcawl.iot_connector.profile.ui.screen.all_profiles

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.profile.domain.IProfileDatasource
import org.alexcawl.iot_connector.ui.util.StateViewModel
import javax.inject.Inject

class AllProfilesViewModel @Inject constructor(
    private val source: IProfileDatasource
) : StateViewModel<AllProfilesScreenState, AllProfilesScreenAction>() {
    private val _state: MutableStateFlow<AllProfilesScreenState> = MutableStateFlow(AllProfilesScreenState.Initial)
    override val state: StateFlow<AllProfilesScreenState> = _state.asStateFlow()

    override fun handle(action: AllProfilesScreenAction) = Unit

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            source.getAllProfiles().collect { profiles ->
                _state.emit(AllProfilesScreenState.Successful(profiles))
            }
        }
    }
}