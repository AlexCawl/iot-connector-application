package org.alexcawl.iot_connector.profile.ui.screen.all_profiles

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.profile.domain.usecase.GetAllProfilesWithSelectionUseCase
import org.alexcawl.iot_connector.profile.domain.usecase.SetSelectedProfileByIdUseCase
import org.alexcawl.iot_connector.ui.util.StateViewModel
import javax.inject.Inject

class AllProfilesViewModel @Inject constructor(
    private val getAllProfilesWithSelection: GetAllProfilesWithSelectionUseCase,
    private val setSelectedProfileByIdUseCase: SetSelectedProfileByIdUseCase
) : StateViewModel<AllProfilesScreenState, AllProfilesScreenAction>() {
    private val _state: MutableStateFlow<AllProfilesScreenState> =
        MutableStateFlow(AllProfilesScreenState.Initial)
    override val state: StateFlow<AllProfilesScreenState> = _state.asStateFlow()

    override fun handle(action: AllProfilesScreenAction) {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            when (action) {
                is AllProfilesScreenAction.SelectProfileById -> setSelectedProfileByIdUseCase(action.id)
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            getAllProfilesWithSelection.invoke().collect {
                _state.emit(AllProfilesScreenState.Successful(it.second, it.first))
            }
        }
    }
}