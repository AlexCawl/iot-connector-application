package org.alexcawl.iot_connector.profile

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.persistence.util.IProfileEntityMapper
import org.alexcawl.iot_connector.profile.data.ProfileService
import org.alexcawl.iot_connector.profile.data.IProfileService
import org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileViewModel
import org.alexcawl.iot_connector.profile.domain.ProfileEntityMapper
import org.alexcawl.iot_connector.profile.domain.ProfileStateMapper
import org.alexcawl.iot_connector.ui.data.IProfileStateMapper

@Module
interface ProfileModule {
    @Binds
    fun bindProfileEntityMapper(mapper: ProfileEntityMapper): IProfileEntityMapper

    @Binds
    fun bindProfileStateMapper(mapper: ProfileStateMapper): IProfileStateMapper

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
    @ViewModelKey(AddProfileViewModel::class)
    fun bindAddProfileScreenViewModel(viewModel: AddProfileViewModel): ViewModel
}