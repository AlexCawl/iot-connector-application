package org.alexcawl.iot_connector.profile.ui.screen.update.edit

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.common.model.ProfileValidationException
import org.alexcawl.iot_connector.common.util.IProfileValidator
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.component.Delete
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.component.NotFound
import java.util.UUID
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val service: IProfileService, private val validator: IProfileValidator
) : ProfileViewModel() {
    private val _profileId: MutableStateFlow<UUID?> = MutableStateFlow(null)

    suspend fun setProfileId(uuid: UUID) = _profileId.emit(uuid)

    override fun handle(action: ProfileScreenAction) {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            when (action) {
                is Delete -> _profileId.collect { profileId ->
                    when (profileId) {
                        null -> _state.emit(NotFound)
                        else -> {
                            service.deleteProfile(profileId)
                            _state.emit(ProfileScreenState.Saving)
                        }
                    }
                }
                else -> super.handle(action)
            }
        }
    }

    override suspend fun saveProfile(screenState: ProfileScreenState.Builder) = try {
        val updatedProfile = validator.validate(
            name = screenState.name,
            host = screenState.host,
            port = screenState.port,
            info = if (screenState.infoOptional) null else screenState.info,
            login = if (screenState.loginOptional) null else screenState.login,
            password = if (screenState.passwordOptional) null else screenState.password
        )
        _profileId.collect { profileId ->
            when (profileId) {
                null -> _state.emit(NotFound)
                else -> {
                    service.updateProfile(profileId, updatedProfile)
                    _state.emit(ProfileScreenState.Saving)
                }
            }
        }
    } catch (exception: ProfileValidationException) {
        _state.emit(
            when (exception) {
                ProfileValidationException.ProfileNameIsEmpty -> screenState.copy(nameMessage = ProfileScreenState.Builder.Message.NULL)
                ProfileValidationException.ConfigurationHostIsEmpty -> screenState.copy(hostMessage = ProfileScreenState.Builder.Message.NULL)
                ProfileValidationException.ConfigurationPortIsEmpty -> screenState.copy(portMessage = ProfileScreenState.Builder.Message.NULL)
                ProfileValidationException.ConfigurationPortIsNotNumber -> screenState.copy(
                    portMessage = ProfileScreenState.Builder.Message.NOT_A_NUMBER
                )
            }
        )
    }

    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            _profileId.collect { id ->
                when (id) {
                    null -> Unit
                    else -> when (val profile = service.getProfile(id)) {
                        null -> _state.emit(NotFound)
                        else -> _state.emit(profile.toState())
                    }
                }
            }
        }
    }

    private companion object {
        fun Profile.toState(): ProfileScreenState {
            return ProfileScreenState.Builder(
                name = name,
                nameMessage = ProfileScreenState.Builder.Message.OK,
                info = info ?: "",
                infoOptional = info == null,
                host = host,
                hostMessage = ProfileScreenState.Builder.Message.OK,
                port = port.toString(),
                portMessage = ProfileScreenState.Builder.Message.OK,
                login = login ?: "",
                loginOptional = login == null,
                password = password ?: "",
                passwordOptional = password == null
            )
        }
    }
}