package org.alexcawl.iot_connector.profile.ui.screen.show.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesScreenState
import org.alexcawl.iot_connector.ui.components.PaddingLarge
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.placeholder.EmptyScreen
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.state.ProfileState
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ShowProfileScreen(
    state: ShowProfilesScreenState,
    onAction: (ShowProfilesScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAddProfile: () -> Unit,
    onNavigateToEditProfile: (UUID) -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.profiles_title),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )
    },
    floatingActionButton = {
        FloatingActionButton(onClick = onNavigateToAddProfile) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
) { paddingValues: PaddingValues ->
    val paddingModifier = Modifier.padding(paddingValues)
    when (state) {
        is ShowProfilesScreenState.Initial -> LoadingScreen(
            modifier = paddingModifier.fillMaxSize(),
            title = {
                Text(
                    text = "Loading profiles",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )

        is ShowProfilesScreenState.Viewing -> when (state.availableProfiles.size) {
            0 -> EmptyScreen(
                modifier = paddingModifier.fillMaxSize(),
                title = {
                    Text(
                        text = stringResource(id = R.string.no_profiles_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                subtitle = {
                    Text(
                        text = stringResource(id = R.string.no_profiles_subtitle),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )

            else -> {
                LazyColumn(
                    modifier = paddingModifier,
                    verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    contentPadding = PaddingValues(
                        start = PaddingMedium,
                        top = PaddingMedium,
                        end = PaddingMedium,
                        bottom = PaddingLarge * 3
                    )
                ) {
                    item(key = SELECTED_PROFILE_LABEL) {
                        AnimatedContent(
                            targetState = state.selectedProfile,
                            label = SELECTED_PROFILE_LABEL
                        ) { selectedProfile ->
                            SelectedProfileCard(
                                profile = selectedProfile,
                                onClick = { onAction(ShowProfilesScreenAction.SelectProfileById(it?.id)) }
                            )
                        }
                    }
                    items(state.availableProfiles, key = { it.id }) { profile ->
                        ProfileCard(
                            profile = profile,
                            onClicked = { onAction(ShowProfilesScreenAction.SelectProfileById(it)) },
                            onEditClicked = { onNavigateToEditProfile(it) }
                        )
                    }
                }
            }
        }
    }
}

private const val SELECTED_PROFILE_LABEL: String = "SelectedProfile"


@ThemedPreview
@Composable
private fun PreviewLoading() {
    val state: ShowProfilesScreenState = ShowProfilesScreenState.Initial

    IoTConnectorTheme {
        ShowProfileScreen(state = state,
            onAction = {},
            onNavigateToAddProfile = {},
            onNavigateToEditProfile = {},
            onNavigateBack = {}
        )
    }
}

@ThemedPreview
@Composable
private fun PreviewEmpty() {
    val state: ShowProfilesScreenState = ShowProfilesScreenState.Viewing(null, listOf())

    IoTConnectorTheme {
        ShowProfileScreen(
            state = state,
            onAction = {},
            onNavigateToAddProfile = {},
            onNavigateToEditProfile = {},
            onNavigateBack = {}
        )
    }
}

@ThemedPreview
@Composable
private fun Preview() {
    val profiles = listOf(
        ProfileState(
            id = UUID.randomUUID(),
            name = "home",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8080,
            login = "user",
            password = "admin",
            info = "my home profile",
            changedAt = System.currentTimeMillis() + 1000L
        ),
        ProfileState(
            id = UUID.randomUUID(),
            name = "work",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8000,
            login = "user",
            info = "my work profile",
            changedAt = System.currentTimeMillis() + 5000L
        ),
        ProfileState(
            id = UUID.randomUUID(),
            name = "default",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8000
        )
    )
    val selected = profiles.first()
    val state = ShowProfilesScreenState.Viewing(selected, profiles)

    IoTConnectorTheme {
        ShowProfileScreen(state = state,
            onAction = {},
            onNavigateToAddProfile = {},
            onNavigateToEditProfile = {},
            onNavigateBack = {}
        )
    }
}