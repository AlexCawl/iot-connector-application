package org.alexcawl.iot_connector.title.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.alexcawl.iot_connector.ui.util.StateViewModel
import javax.inject.Inject

class TitleScreenViewModel @Inject constructor() : StateViewModel<TitleScreenState, TitleScreenAction>() {
    private val _state: MutableStateFlow<TitleScreenState> = MutableStateFlow(TitleScreenState.Initial)
    override val state: StateFlow<TitleScreenState> = _state.asStateFlow()

    override fun handle(action: TitleScreenAction) = Unit
}