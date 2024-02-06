package org.alexcawl.iot_connector.profile.ui.update_screen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.ui.util.StateViewModel

abstract class ProfileViewModel : StateViewModel<ProfileScreenState, ProfileScreenAction>() {
    protected val _state: MutableStateFlow<ProfileScreenState> =
        MutableStateFlow(ProfileScreenState.Initial)
    override val state: StateFlow<ProfileScreenState> = _state.asStateFlow()

    override fun handle(action: ProfileScreenAction) {
        viewModelScope.launch {
            when (action) {
                is ProfileScreenAction.SetName -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(name = action.name))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetInfo -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(info = action.info))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetInfoType -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(infoOptional = action.optional))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetHost -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(host = action.host))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetPort -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(port = action.port))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetLogin -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(login = action.login))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetLoginType -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(loginOptional = action.optional))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetPassword -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(password = action.password))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.SetPasswordType -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> _state.emit(screenState.copy(passwordOptional = action.optional))
                    else -> throw IllegalStateException()
                }

                is ProfileScreenAction.Save -> when (val screenState = state.value) {
                    is ProfileScreenState.Builder -> saveProfile(screenState)
                    else -> throw IllegalStateException()
                }

                else -> throw IllegalStateException()
            }
        }
    }
    protected abstract suspend fun saveProfile(screenState: ProfileScreenState.Builder)
}

