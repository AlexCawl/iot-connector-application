package org.alexcawl.iot_connector.profile

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.profile.data.ProfileEntityMapper
import org.alexcawl.iot_connector.profile.data.ProfileRepository
import org.alexcawl.iot_connector.profile.data.ProfileStateMapper
import org.alexcawl.iot_connector.profile.domain.IProfileEntityMapper
import org.alexcawl.iot_connector.profile.domain.IProfileRepository
import org.alexcawl.iot_connector.profile.domain.IProfileStateMapper
import org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileViewModel
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileViewModel

@Module
interface ProfileModule {
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

    @Binds
    fun bindRepository(repository: ProfileRepository): IProfileRepository

    @Binds
    fun bindEntityMapper(mapper: ProfileEntityMapper): IProfileEntityMapper

    @Binds
    fun bindStateMapper(mapper: ProfileStateMapper): IProfileStateMapper
}