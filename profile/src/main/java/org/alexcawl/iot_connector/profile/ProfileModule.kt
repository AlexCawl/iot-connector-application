package org.alexcawl.iot_connector.profile

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.profile.data.ProfileService
import org.alexcawl.iot_connector.profile.domain.IProfileService
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenViewModel
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.screen.edit.EditProfileViewModel
import org.alexcawl.iot_connector.profile.util.IProfileMapper
import org.alexcawl.iot_connector.profile.util.ProfileMapper

@Module
interface ProfileModule {
    @Binds
    fun bindProfileMapper(mapper: ProfileMapper): IProfileMapper

    @Binds
    fun bindProfileDatasource(profileDatasource: ProfileService): IProfileService

    @Binds
    @IntoMap
    @ViewModelKey(ShowProfilesViewModel::class)
    fun bindAllProfilesViewModel(viewModel: ShowProfilesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    fun bindEditProfileScreenViewModel(viewModel: EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddProfileScreenViewModel::class)
    fun bindAddProfileScreenViewModel(viewModel: AddProfileScreenViewModel): ViewModel
}