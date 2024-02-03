package org.alexcawl.iot_connector.profile.ui.screen.update.add

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.profile.domain.usecase.CreateProfileUseCase
import org.alexcawl.iot_connector.profile.domain.usecase.ProfileValidationException
import org.alexcawl.iot_connector.profile.domain.usecase.ValidateProfileUseCase
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileViewModel
import javax.inject.Inject

class AddProfileViewModel @Inject constructor(
    private val createProfile: CreateProfileUseCase,
    private val validateProfile: ValidateProfileUseCase
) : ProfileViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            _state.emit(ProfileScreenState.Builder())
        }
    }

    override suspend fun saveProfile(screenState: ProfileScreenState.Builder) = try {
        val profile = validateProfile(
            name = screenState.name,
            host = screenState.host,
            port = screenState.port,
            info = if (screenState.infoOptional) null else screenState.info,
            login = if (screenState.loginOptional) null else screenState.login,
            password = if (screenState.passwordOptional) null else screenState.password
        )
        createProfile(profile)
        _state.emit(ProfileScreenState.Saving)
    } catch (exception: ProfileValidationException) {
        _state.emit(
            when (exception) {
                ProfileValidationException.ProfileNameIsEmpty -> screenState.copy(nameMessage = ProfileScreenState.Builder.Message.NULL)
                ProfileValidationException.ConfigurationHostIsEmpty -> screenState.copy(hostMessage = ProfileScreenState.Builder.Message.NULL)
                ProfileValidationException.ConfigurationPortIsEmpty -> screenState.copy(portMessage = ProfileScreenState.Builder.Message.NULL)
                ProfileValidationException.ConfigurationPortIsNotNumber -> screenState.copy(portMessage = ProfileScreenState.Builder.Message.NOT_A_NUMBER)
            }
        )
    }
}