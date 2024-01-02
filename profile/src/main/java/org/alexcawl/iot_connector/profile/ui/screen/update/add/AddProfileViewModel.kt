package org.alexcawl.iot_connector.profile.ui.screen.update.add

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.common.model.ProfileBuildException
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.common.model.ProfileBuilder
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState
import org.alexcawl.iot_connector.ui.util.StateViewModel
import java.lang.IllegalStateException
import javax.inject.Inject

class AddProfileViewModel @Inject constructor(
    private val service: IProfileService
) : StateViewModel<AddProfileScreenState, AddProfileScreenAction>() {
    private val _state: MutableStateFlow<AddProfileScreenState> =
        MutableStateFlow(AddProfileScreenState.Initial)
    override val state: StateFlow<AddProfileScreenState> = _state.asStateFlow()

    override fun handle(action: AddProfileScreenAction) {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            when (action) {
                is AddProfileScreenAction.SetName -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(name = action.name)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetInfo -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(info = action.info)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetInfoType -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(infoOptional = action.optional)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetHost -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(host = action.host)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetPort -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(port = action.port)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetLogin -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(login = action.login)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetLoginType -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(loginOptional = action.optional)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetPassword -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(password = action.password)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.SetPasswordType -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(passwordOptional = action.optional)))
                    }
                    else -> throw IllegalStateException()
                }

                is AddProfileScreenAction.AddProfile -> when (val currentState = state.value) {
                    is AddProfileScreenState.Building -> {
                        val profileState = currentState.profileScreenState
                        val builder = ProfileBuilder(
                            name = profileState.name,
                            info = if (profileState.infoOptional) null else profileState.info,
                            host = profileState.host,
                            port = profileState.port,
                            login = if (profileState.loginOptional) null else profileState.login,
                            password = if (profileState.passwordOptional) null else profileState.password
                        )
                        _state.emit(
                            try {
                                val profile = builder.build()
                                service.createProfile(profile)
                                AddProfileScreenState.Completed
                            } catch (exception: ProfileBuildException) {
                                when (exception) {
                                    ProfileBuildException.ProfileNameException -> currentState.copy(
                                        profileState.copy(nameMessage = ProfileScreenState.Message.NULL)
                                    )

                                    ProfileBuildException.ConfigurationHostIsEmpty -> currentState.copy(
                                        profileState.copy(hostMessage = ProfileScreenState.Message.NULL)
                                    )

                                    ProfileBuildException.ConfigurationPortIsEmpty -> currentState.copy(
                                        profileState.copy(portMessage = ProfileScreenState.Message.NULL)
                                    )

                                    ProfileBuildException.ConfigurationPortIsNotNumber -> currentState.copy(
                                        profileState.copy(portMessage = ProfileScreenState.Message.NOT_A_NUMBER)
                                    )
                                }
                            }
                        )
                    }
                    is AddProfileScreenState.Completed -> Unit
                    is AddProfileScreenState.Initial -> throw IllegalStateException()
                }
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            _state.emit(
                AddProfileScreenState.Building(ProfileScreenState())
            )
        }
    }
}