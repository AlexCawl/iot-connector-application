package org.alexcawl.iot_connector.profile

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.profile.data.ProfileService
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.AllProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.screen.edit_profile.EditProfileViewModel
import org.alexcawl.iot_connector.profile.util.IMQTTConfigurationMapper
import org.alexcawl.iot_connector.profile.util.IProfileMapper
import org.alexcawl.iot_connector.profile.util.MQTTConfigurationMapper
import org.alexcawl.iot_connector.profile.util.ProfileMapper

@Module
interface ProfileModule {
    @Binds
    fun bindProfileMapper(mapper: ProfileMapper): IProfileMapper

    @Binds
    fun bindConfigurationMapper(mapper: MQTTConfigurationMapper): IMQTTConfigurationMapper

    @Binds
    fun bindProfileDatasource(profileDatasource: ProfileService): IProfileService

    @Binds
    @IntoMap
    @ViewModelKey(AllProfilesViewModel::class)
    fun bindAllProfilesViewModel(viewModel: AllProfilesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    fun bindEditProfileScreenViewModel(viewModel: EditProfileViewModel): ViewModel
}