package org.alexcawl.iot_connector.profile.ui.screen.show

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

class ShowProfilesViewModel @Inject constructor(
    private val getAllProfilesWithSelection: GetAllProfilesWithSelectionUseCase,
    private val setSelectedProfileByIdUseCase: SetSelectedProfileByIdUseCase
) : StateViewModel<ShowProfilesScreenState, ShowProfilesScreenAction>() {
    private val _state: MutableStateFlow<ShowProfilesScreenState> =
        MutableStateFlow(ShowProfilesScreenState.Initial)
    override val state: StateFlow<ShowProfilesScreenState> = _state.asStateFlow()

    override fun handle(action: ShowProfilesScreenAction) {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            when (action) {
                is ShowProfilesScreenAction.SelectProfileById -> setSelectedProfileByIdUseCase(action.id)
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            getAllProfilesWithSelection.invoke().collect {
                _state.emit(ShowProfilesScreenState.Successful(it.second, it.first))
            }
        }
    }
}