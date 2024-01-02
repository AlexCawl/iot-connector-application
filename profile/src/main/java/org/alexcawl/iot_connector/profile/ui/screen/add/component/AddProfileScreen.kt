package org.alexcawl.iot_connector.profile.ui.screen.add.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenState
import org.alexcawl.iot_connector.ui.components.LoadingPlaceholder
import org.alexcawl.iot_connector.ui.components.MaterialSpacer
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.input.DialogTextFieldState
import org.alexcawl.iot_connector.ui.components.input.OptionalDialogTextField
import org.alexcawl.iot_connector.ui.components.input.RequiredDialogTextField
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

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
                            contentDescription = "Localized description",
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
            is AddProfileScreenState.Building -> {
                Column(
                    modifier = paddingModifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
                    horizontalAlignment = Alignment.Start
                ) {
                    RequiredDialogTextField(
                        state = DialogTextFieldState(
                            value = state.name,
                            label = "Name",
                            errorMessage = state.nameMessage.toText()
                        ),
                        onFieldValueChange = { onAction(AddProfileScreenAction.SetName(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    OptionalDialogTextField(
                        visible = state.infoOptional.not(),
                        onVisibilityChange = {
                            onAction(AddProfileScreenAction.SetInfoType(state.infoOptional.not()))
                        },
                        state = DialogTextFieldState(
                            value = state.info,
                            label = "Info"
                        ),
                        onFieldValueChange = { onAction(AddProfileScreenAction.SetInfo(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    RequiredDialogTextField(
                        state = DialogTextFieldState(
                            value = state.host,
                            label = "Host",
                            errorMessage = state.hostMessage.toText()
                        ),
                        onFieldValueChange = { onAction(AddProfileScreenAction.SetHost(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    RequiredDialogTextField(
                        state = DialogTextFieldState(
                            value = state.port,
                            label = "Port",
                            errorMessage = state.portMessage.toText()
                        ),
                        onFieldValueChange = { onAction(AddProfileScreenAction.SetPort(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    OptionalDialogTextField(
                        visible = state.loginOptional.not(),
                        onVisibilityChange = { onAction(AddProfileScreenAction.SetLoginType(state.loginOptional.not())) },
                        state = DialogTextFieldState(value = state.login, label = "Login"),
                        onFieldValueChange = { onAction(AddProfileScreenAction.SetLogin(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    OptionalDialogTextField(
                        visible = state.passwordOptional.not(),
                        onVisibilityChange = { onAction(AddProfileScreenAction.SetPasswordType(state.passwordOptional.not())) },
                        state = DialogTextFieldState(value = state.password, label = "Password"),
                        onFieldValueChange = { onAction(AddProfileScreenAction.SetPassword(it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                }
            }
            AddProfileScreenState.Completed -> {
                LoadingPlaceholder(
                    modifier = paddingModifier.fillMaxSize(),
                    title = {
                        Text(text = "Updating skeletons")
                    }
                )
                LaunchedEffect(key1 = null) {
                    onNavigateBack()
                }
            }
        }
    }
}

@Composable
fun AddProfileScreenState.Building.Message.toText(): String? = when (this) {
    AddProfileScreenState.Building.Message.OK -> null
    AddProfileScreenState.Building.Message.NULL -> stringResource(id = R.string.cannot_be_null)
    AddProfileScreenState.Building.Message.NOT_A_NUMBER -> stringResource(id = R.string.not_a_number)
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val state = AddProfileScreenState.Building()

    IoTConnectorTheme {
        AddProfileScreen(state = state, onAction = {}, onNavigateBack = { /*TODO*/ })
    }
}