package org.alexcawl.iot_connector.profile.ui.screen.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.ui.components.MaterialSpacer
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.input.DialogTextFieldState
import org.alexcawl.iot_connector.ui.components.input.OptionalDialogTextField
import org.alexcawl.iot_connector.ui.components.input.RequiredDialogTextField

@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    onNameChange: (String) -> Unit,
    onInfoChange: (String) -> Unit,
    onInfoVisibilityChange: (Boolean) -> Unit,
    onHostChange: (String) -> Unit,
    onPortChange: (String) -> Unit,
    onLoginChange: (String) -> Unit,
    onLoginVisibilityChange: (Boolean) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        RequiredDialogTextField(
            state = DialogTextFieldState(
                value = state.name,
                label = "Name",
                errorMessage = state.nameMessage.toText()
            ),
            onFieldValueChange = onNameChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
        )
        MaterialSpacer()

        OptionalDialogTextField(
            visible = state.infoOptional.not(),
            onVisibilityChange = onInfoVisibilityChange,
            state = DialogTextFieldState(
                value = state.info,
                label = "Info"
            ),
            onFieldValueChange = onInfoChange,
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
            onFieldValueChange = onHostChange,
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
            onFieldValueChange = onPortChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
        )
        MaterialSpacer()

        OptionalDialogTextField(
            visible = state.loginOptional.not(),
            onVisibilityChange = onLoginVisibilityChange,
            state = DialogTextFieldState(value = state.login, label = "Login"),
            onFieldValueChange = onLoginChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
        )
        MaterialSpacer()

        OptionalDialogTextField(
            visible = state.passwordOptional.not(),
            onVisibilityChange = onPasswordVisibilityChange,
            state = DialogTextFieldState(value = state.password, label = "Password"),
            onFieldValueChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
        )
    }
}

@Composable
fun ProfileScreenState.Message.toText(): String? = when (this) {
    ProfileScreenState.Message.OK -> null
    ProfileScreenState.Message.NULL -> stringResource(id = R.string.cannot_be_null)
    ProfileScreenState.Message.NOT_A_NUMBER -> stringResource(id = R.string.not_a_number)
}