package org.alexcawl.iot_connector.profile.ui.screen.all_profiles.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.common.model.MQTTConfiguration
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.AllProfilesScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.all_profiles.AllProfilesScreenState
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProfilesScreen(
    state: AllProfilesScreenState,
    onAction: (AllProfilesScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    onAddProfileAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier, topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(text = "Your profiles setups", maxLines = 1, overflow = TextOverflow.Ellipsis)
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back to page")
                }
            },
            scrollBehavior = scrollBehavior
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = onAddProfileAction) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add profile")
        }
    }) {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                is AllProfilesScreenState.Initial -> {
                    AllProfilesScreenLoading(modifier = Modifier.fillMaxSize())
                }

                is AllProfilesScreenState.Successful -> {
                    when (state.profiles.size) {
                        0 -> {
                            AllProfilesScreenEmpty(modifier = Modifier.fillMaxSize())
                        }
                        else -> {
                            AllProfilesScreenContent(
                                profiles = state.profiles,
                                selectedId = state.selectedProfileId,
                                onSelectionAction = { /*TODO*/ },
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }

                is AllProfilesScreenState.Fail -> TODO()
            }
        }
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoading() {
    val state: AllProfilesScreenState = AllProfilesScreenState.Initial

    IoTConnectorTheme {
        AllProfilesScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
            onAddProfileAction = {})
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewEmpty() {
    val state: AllProfilesScreenState = AllProfilesScreenState.Successful(listOf(), null)

    IoTConnectorTheme {
        AllProfilesScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
            onAddProfileAction = {})
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

    IoTConnectorTheme {

    }
}