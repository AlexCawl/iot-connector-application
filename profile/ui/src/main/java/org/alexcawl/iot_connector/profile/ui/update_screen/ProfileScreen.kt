package org.alexcawl.iot_connector.profile.ui.update_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.profile.ui.R
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.components.Spacer
import org.alexcawl.iot_connector.ui.components.input.DialogTextFieldState
import org.alexcawl.iot_connector.ui.components.input.OptionalDialogTextField
import org.alexcawl.iot_connector.ui.components.input.RequiredDialogTextField
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreen(
    state: ProfileScreenState,
    onAction: (ProfileScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    title: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        CenterAlignedTopAppBar(
            title = title,
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                TextButton(
                    onClick = { onAction(ProfileScreenAction.Save) },
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
            }
        )
    },
    floatingActionButton = floatingActionButton
) { paddingValues: PaddingValues ->
    val paddingModifier = Modifier.padding(paddingValues)
    when (state) {
        is ProfileScreenState.Initial -> LoadingScreen(
            modifier = paddingModifier.fillMaxSize()
        )

        is ProfileScreenState.Builder -> {
            Column(
                modifier = paddingModifier
                    .verticalScroll(rememberScrollState())
                    .padding(ExtendedTheme.padding.medium),
                verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = state.name,
                        label = stringResource(id = R.string.profile_name),
                        errorMessage = state.nameMessage.toText()
                    ),
                    onFieldValueChange = { onAction(ProfileScreenAction.SetName(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OptionalDialogTextField(
                    visible = state.infoOptional.not(),
                    onVisibilityChange = { onAction(ProfileScreenAction.SetInfoType(it.not())) },
                    state = DialogTextFieldState(
                        value = state.info,
                        label = stringResource(id = R.string.profile_info)
                    ),
                    onFieldValueChange = { onAction(ProfileScreenAction.SetInfo(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = state.host,
                        label = stringResource(id = R.string.profile_host),
                        errorMessage = state.hostMessage.toText()
                    ),
                    onFieldValueChange = { onAction(ProfileScreenAction.SetHost(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = state.port,
                        label = stringResource(id = R.string.profile_port),
                        errorMessage = state.portMessage.toText()
                    ),
                    onFieldValueChange = { onAction(ProfileScreenAction.SetPort(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OptionalDialogTextField(
                    visible = state.loginOptional.not(),
                    onVisibilityChange = { onAction(ProfileScreenAction.SetLoginType(it.not())) },
                    state = DialogTextFieldState(
                        value = state.login,
                        label = stringResource(id = R.string.profile_login)
                    ),
                    onFieldValueChange = { onAction(ProfileScreenAction.SetLogin(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OptionalDialogTextField(
                    visible = state.passwordOptional.not(),
                    onVisibilityChange = { onAction(ProfileScreenAction.SetPasswordType(it.not())) },
                    state = DialogTextFieldState(
                        value = state.password,
                        label = stringResource(id = R.string.profile_password)
                    ),
                    onFieldValueChange = { onAction(ProfileScreenAction.SetPassword(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(ExtendedTheme.padding.large * 2)
                        .background(color = Color.Transparent)
                )
            }
        }

        is ProfileScreenState.Saving -> LaunchedEffect(key1 = null) {
            onNavigateBack()
        }
    }
}

@Composable
internal fun ProfileScreenState.Builder.Message.toText(): String? = when (this) {
    ProfileScreenState.Builder.Message.OK -> null
    ProfileScreenState.Builder.Message.NULL -> stringResource(id = R.string.cannot_be_null)
    ProfileScreenState.Builder.Message.NOT_A_NUMBER -> stringResource(id = R.string.not_a_number)
}

@ThemedPreview
@Composable
private fun Preview() {
    val state = ProfileScreenState.Builder()

    IoTConnectorTheme {
        ProfileScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
            title = {},
            floatingActionButton = {}
        )
    }
}