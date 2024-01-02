package org.alexcawl.iot_connector.profile.ui.screen.update.edit.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreen
import org.alexcawl.iot_connector.profile.ui.screen.update.ProfileScreenState
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.update.edit.EditProfileScreenState
import org.alexcawl.iot_connector.ui.components.LoadingPlaceholder
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    state: EditProfileScreenState,
    onAction: (EditProfileScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Add profile",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = { onAction(EditProfileScreenAction.EditProfile) },
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text(
                            text = stringResource(id = R.string.save).uppercase(),
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Clip,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(EditProfileScreenAction.DeleteProfile) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    ) { paddingValues: PaddingValues ->
        val paddingModifier = Modifier.padding(paddingValues)
        when (state) {
            EditProfileScreenState.Initial -> LoadingPlaceholder(modifier = paddingModifier
                .fillMaxSize())
            is EditProfileScreenState.Building -> ProfileScreen(
                state = state.profileScreenState,
                onNameChange = { onAction(EditProfileScreenAction.SetName(it)) },
                onInfoChange = { onAction(EditProfileScreenAction.SetInfo(it)) },
                onInfoVisibilityChange = { onAction(EditProfileScreenAction.SetInfoType(it.not())) },
                onHostChange = { onAction(EditProfileScreenAction.SetHost(it)) },
                onPortChange = { onAction(EditProfileScreenAction.SetPort(it)) },
                onLoginChange = { onAction(EditProfileScreenAction.SetLogin(it)) },
                onLoginVisibilityChange = { onAction(EditProfileScreenAction.SetLoginType(it.not())) },
                onPasswordChange = { onAction(EditProfileScreenAction.SetPassword(it)) },
                onPasswordVisibilityChange = { onAction(EditProfileScreenAction.SetPasswordType(it.not())) },
                modifier = Modifier.padding(paddingValues)
            )
            else -> LaunchedEffect(key1 = null) {
                onNavigateBack()
            }
        }
    }
}

@ThemedPreview
@Composable
private fun Preview() {
    val state = EditProfileScreenState.Building(ProfileScreenState())

    IoTConnectorTheme {
        EditProfileScreen(
            state = state,
            onAction = {},
            onNavigateBack = {}
        )
    }
}