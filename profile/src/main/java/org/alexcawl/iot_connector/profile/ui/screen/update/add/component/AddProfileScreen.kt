package org.alexcawl.iot_connector.profile.ui.screen.update.add.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.update.add.AddProfileScreenState
import org.alexcawl.iot_connector.ui.components.LoadingPlaceholder
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProfileScreen(
    state: AddProfileScreenState,
    onAction: (AddProfileScreenAction) -> Unit,
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
                        onClick = { onAction(AddProfileScreenAction.AddProfile) },
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
        }
    ) { paddingValues: PaddingValues ->
        val paddingModifier = Modifier.padding(paddingValues)
        when (state) {
            AddProfileScreenState.Initial -> LoadingPlaceholder(modifier = paddingModifier
                .fillMaxSize())
            is AddProfileScreenState.Building -> ProfileScreen(
                state = state.profileScreenState,
                onNameChange = { onAction(AddProfileScreenAction.SetName(it)) },
                onInfoChange = { onAction(AddProfileScreenAction.SetInfo(it)) },
                onInfoVisibilityChange = { onAction(AddProfileScreenAction.SetInfoType(it.not())) },
                onHostChange = { onAction(AddProfileScreenAction.SetHost(it)) },
                onPortChange = { onAction(AddProfileScreenAction.SetPort(it)) },
                onLoginChange = { onAction(AddProfileScreenAction.SetLogin(it)) },
                onLoginVisibilityChange = { onAction(AddProfileScreenAction.SetLoginType(it.not())) },
                onPasswordChange = { onAction(AddProfileScreenAction.SetPassword(it)) },
                onPasswordVisibilityChange = {
                    onAction(
                        AddProfileScreenAction.SetPasswordType(
                            it.not()
                        )
                    )
                },
                modifier = Modifier.padding(paddingValues)
            )
            AddProfileScreenState.Completed -> LaunchedEffect(key1 = null) {
                onNavigateBack()
            }
        }
    }
}

@ThemedPreview
@Composable
private fun Preview() {
    val state = AddProfileScreenState.Building(ProfileScreenState())

    IoTConnectorTheme {
        AddProfileScreen(state = state, onAction = {}, onNavigateBack = {})
    }
}