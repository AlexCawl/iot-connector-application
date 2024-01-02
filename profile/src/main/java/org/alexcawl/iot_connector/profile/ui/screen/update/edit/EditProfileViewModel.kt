package org.alexcawl.iot_connector.profile.ui.screen.update.edit

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.common.model.ProfileBuildException
import org.alexcawl.iot_connector.common.model.ProfileBuilder
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState
import org.alexcawl.iot_connector.ui.util.StateViewModel
import java.util.UUID
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val service: IProfileService
) : StateViewModel<EditProfileScreenState, EditProfileScreenAction>() {
    private val _state: MutableStateFlow<EditProfileScreenState> =
        MutableStateFlow(EditProfileScreenState.Initial)
    override val state: StateFlow<EditProfileScreenState> = _state.asStateFlow()
    private val _profileId: MutableStateFlow<UUID?> = MutableStateFlow(null)

    override fun handle(action: EditProfileScreenAction) {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            when (action) {
                is EditProfileScreenAction.SelectProfileById -> _profileId.emit(action.id)

                is EditProfileScreenAction.DeleteProfile -> when (val id = _profileId.value) {
                    null -> throw IllegalStateException()
                    else -> service.getProfile(id).collect { profile ->
                        when (profile) {
                            null -> Unit
                            else -> service.deleteProfile(profile)
                        }
                    }
                }

                is EditProfileScreenAction.SetName -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(name = action.name)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetInfo -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(info = action.info)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetInfoType -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(infoOptional = action.optional)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetHost -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(host = action.host)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetPort -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(port = action.port)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetLogin -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(login = action.login)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetLoginType -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(loginOptional = action.optional)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetPassword -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(password = action.password)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.SetPasswordType -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
                        val profile = currentState.profileScreenState
                        _state.emit(currentState.copy(profile.copy(passwordOptional = action.optional)))
                    }
                    else -> throw IllegalStateException()
                }

                is EditProfileScreenAction.EditProfile -> when (val currentState = state.value) {
                    is EditProfileScreenState.Building -> {
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
                                EditProfileScreenState.Completed
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
                    is EditProfileScreenState.Completed -> Unit
                    is EditProfileScreenState.Initial -> throw IllegalStateException()
                    is EditProfileScreenState.ProfileNotFound -> throw IllegalStateException()
                }
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            _profileId.collect { id ->
                when (id) {
                    null -> Unit
                    else -> service.getProfile(id).collect { profile ->
                        when (profile) {
                            null -> _state.emit(EditProfileScreenState.ProfileNotFound)
                            else -> _state.emit(EditProfileScreenState.Building(profile.toState()))
                        }
                    }
                }
            }
        }
    }
}

private fun Profile.toState(): ProfileScreenState {
    return ProfileScreenState(
        name = name,
        nameMessage = ProfileScreenState.Message.OK,
        info = info ?: "",
        infoOptional = info == null,
        host = host,
        hostMessage = ProfileScreenState.Message.OK,
        port = port.toString(),
        portMessage = ProfileScreenState.Message.OK,
        login = login ?: "",
        loginOptional = login == null,
        password = password ?: "",
        passwordOptional = password == null
    )
}