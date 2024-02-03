package org.alexcawl.iot_connector.profile.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.alexcawl.iot_connector.di.ViewModelKey
import org.alexcawl.iot_connector.profile.data.mapper.ProfileEntityMapper
import org.alexcawl.iot_connector.profile.data.repository.ProfileRepository
import org.alexcawl.iot_connector.profile.domain.mapper.ProfileStateMapper
import org.alexcawl.iot_connector.profile.data.mapper.IProfileEntityMapper
import org.alexcawl.iot_connector.profile.data.repository.IProfileRepository
import org.alexcawl.iot_connector.profile.domain.mapper.IProfileStateMapper
import org.alexcawl.iot_connector.profile.ui.show_screen.ShowProfilesViewModel
import org.alexcawl.iot_connector.profile.ui.update_screen.add_screen.AddProfileViewModel
import org.alexcawl.iot_connector.profile.ui.update_screen.edit_screen.EditProfileViewModel

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