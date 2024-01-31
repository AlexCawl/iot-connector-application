package org.alexcawl.iot_connector.ui.components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun OptionalDialogTextField(
    visible: Boolean,
    onVisibilityChange: (Boolean) -> Unit,
    state: DialogTextFieldState,
    onFieldValueChange: (fieldValue: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(ExtendedTheme.padding.medium)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.small, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.label,
                    minLines = 1,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis
                )
                Checkbox(checked = visible, onCheckedChange = onVisibilityChange)
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                DialogTextField(
                    state = state,
                    onFieldValueChange = onFieldValueChange,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@ThemedPreview
@Composable
private fun StaticPreview() {
    IoTConnectorTheme {
        Scaffold { paddingValues: PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(ExtendedTheme.padding.medium),
                verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                OptionalDialogTextField(
                    visible = false,
                    onVisibilityChange = {},
                    state = DialogTextFieldState(
                        value = "admin",
                        label = "Username",
                        errorMessage = "Username should contain digits!"
                    ),
                    onFieldValueChange = {}
                )
                OptionalDialogTextField(
                    visible = false,
                    onVisibilityChange = {},
                    state = DialogTextFieldState(
                        value = "",
                        label = "Username",
                    ),
                    onFieldValueChange = {}
                )
                OptionalDialogTextField(
                    visible = true,
                    onVisibilityChange = {},
                    state = DialogTextFieldState(
                        value = "admin",
                        label = "Username",
                        errorMessage = "Username should contain digits!"
                    ),
                    onFieldValueChange = {}
                )
                OptionalDialogTextField(
                    visible = true,
                    onVisibilityChange = {},
                    state = DialogTextFieldState(
                        value = "",
                        label = "Username",
                    ),
                    onFieldValueChange = {}
                )
            }
        }
    }
}

@ThemedPreview
@Composable
private fun DynamicPreview() {
    var usernameState: DialogTextFieldState by remember {
        mutableStateOf(
            DialogTextFieldState(
                value = "", label = "Select username:", errorMessage = null
            )
        )
    }
    var passwordRequired: Boolean by remember { mutableStateOf(true) }
    var passwordState: DialogTextFieldState by remember {
        mutableStateOf(
            DialogTextFieldState(
                value = "1234", label = "Select password:", errorMessage = "Password is too weak!"
            )
        )
    }

    IoTConnectorTheme {
        Scaffold { paddingValues: PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(ExtendedTheme.padding.medium),
                verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                OptionalDialogTextField(
                    visible = true,
                    onVisibilityChange = {},
                    state = usernameState,
                    onFieldValueChange = { usernameState = usernameState.copy(value = it) },
                    modifier = Modifier.fillMaxWidth()
                )

                OptionalDialogTextField(
                    visible = passwordRequired,
                    onVisibilityChange = { passwordRequired = passwordRequired.not() },
                    state = passwordState,
                    onFieldValueChange = {
                        passwordState = passwordState.copy(
                            value = it,
                            errorMessage = if (it.length < 5) "Password is too weak!" else null
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}