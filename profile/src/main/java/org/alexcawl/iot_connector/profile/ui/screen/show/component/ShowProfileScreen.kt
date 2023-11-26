package org.alexcawl.iot_connector.profile.ui.screen.show.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.common.model.MQTTConfiguration
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.show.ShowProfilesScreenState
import org.alexcawl.iot_connector.ui.components.LoadingPlaceholder
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowProfilesScreen(
    state: ShowProfilesScreenState,
    onAction: (ShowProfilesScreenAction) -> Unit,
    onNavigateToAddProfile: () -> Unit,
    onNavigateToEditProfile: (UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ), title = {
                    Text(
                        text = stringResource(id = R.string.profiles_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAddProfile) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add profile")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                is ShowProfilesScreenState.Initial -> {
                    LoadingPlaceholder(modifier = Modifier.fillMaxSize())
                }

                is ShowProfilesScreenState.Successful -> {
                    when (state.profiles.size) {
                        0 -> {
                            ProfilesEmptyScreen(modifier = Modifier.fillMaxSize())
                        }

                        else -> {
                            AllProfilesScreenContent(
                                profiles = state.profiles,
                                selected = state.selectedProfileId,
                                onSelectProfile = { id ->
                                    onAction(
                                        ShowProfilesScreenAction.SelectProfileById(
                                            when (id) {
                                                state.selectedProfileId -> null
                                                else -> id
                                            }
                                        )
                                    )
                                },
                                onEditProfile = onNavigateToEditProfile,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                is ShowProfilesScreenState.Fail -> TODO()
            }
        }
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoading() {
    val state: ShowProfilesScreenState = ShowProfilesScreenState.Initial

    IoTConnectorTheme {
        ShowProfilesScreen(
            state = state,
            onAction = {},
            onNavigateToAddProfile = {},
            onNavigateToEditProfile = {}
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewEmpty() {
    val state: ShowProfilesScreenState = ShowProfilesScreenState.Successful(listOf(), null)

    IoTConnectorTheme {
        ShowProfilesScreen(
            state = state,
            onAction = {},
            onNavigateToAddProfile = {},
            onNavigateToEditProfile = {}
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val profiles = listOf(
        Profile(
            id = UUID.randomUUID(),
            name = "home",
            createdAt = System.currentTimeMillis(),
            configuration = MQTTConfiguration(
                host = "localhost", port = 8080, login = "user", password = "admin"
            ),
            info = "my home profile",
            changedAt = System.currentTimeMillis() + 1000L
        ), Profile(
            id = UUID.randomUUID(),
            name = "work",
            createdAt = System.currentTimeMillis(),
            configuration = MQTTConfiguration(
                host = "localhost", port = 8000, login = "user"
            ),
            info = "my work profile",
            changedAt = System.currentTimeMillis() + 5000L
        ), Profile(
            id = UUID.randomUUID(),
            name = "default",
            createdAt = System.currentTimeMillis(),
            configuration = MQTTConfiguration(
                host = "localhost", port = 8000
            )
        )
    )
    val selected: UUID = profiles.first().id
    val state = ShowProfilesScreenState.Successful(profiles, selected)

    IoTConnectorTheme {
        ShowProfilesScreen(
            state = state,
            onAction = {},
            onNavigateToAddProfile = {},
            onNavigateToEditProfile = {}
        )
    }
}