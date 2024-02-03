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

@Module
interface ProfileModule {
    @Binds
    @IntoMap
    @ViewModelKey(org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel::class)
    fun bindAllProfilesViewModel(viewModel: org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileViewModel::class)
    fun bindEditProfileScreenViewModel(viewModel: org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileViewModel::class)
    fun bindAddProfileScreenViewModel(viewModel: org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileViewModel): ViewModel

    @Binds
    fun bindRepository(repository: ProfileRepository): IProfileRepository

    @Binds
    fun bindEntityMapper(mapper: ProfileEntityMapper): IProfileEntityMapper

    @Binds
    fun bindStateMapper(mapper: ProfileStateMapper): IProfileStateMapper
}