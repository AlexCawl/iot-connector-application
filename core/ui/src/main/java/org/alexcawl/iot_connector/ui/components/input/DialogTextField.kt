package org.alexcawl.iot_connector.ui.components.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.dialog.EditActionDialogHolder
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview


@Composable
fun DialogTextField(
    state: DialogTextFieldState,
    onFieldValueChange: (fieldValue: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDialogShown: Boolean by remember { mutableStateOf(false) }

    OutlinedTextField(value = state.value,
        onValueChange = {},
        modifier = modifier,
        readOnly = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        minLines = 1,
        maxLines = 4,
        trailingIcon = {
            when (state.errorMessage) {
                null -> Icon(
                    imageVector = Icons.Default.Create, contentDescription = null
                )

                else -> Icon(
                    imageVector = Icons.Default.Warning, contentDescription = null
                )
            }
        },
        isError = state.errorMessage != null,
        supportingText = {
            when (state.errorMessage) {
                null -> Unit
                else -> Text(
                    text = state.errorMessage,
                    minLines = 1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        shape = MaterialTheme.shapes.large,
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        isDialogShown = true
                    }
                }
            }
        })

    EditActionDialogHolder(
        isShown = isDialogShown,
        initialValue = state.value,
        title = {
            Text(
                text = state.label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                minLines = 1,
                style = MaterialTheme.typography.titleLarge
            )
        },
        onSubmitRequest = {
            onFieldValueChange(it)
            isDialogShown = false
        },
        onDismissRequest = { isDialogShown = false },
    )
}

@Immutable
data class DialogTextFieldState(
    val value: String, val label: String, val errorMessage: String? = null
)

@ThemedPreview
@Composable
private fun StaticPreview() {
    IoTConnectorTheme {
        Scaffold { paddingValues: PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(PaddingMedium),
                verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                DialogTextField(
                    state = DialogTextFieldState(
                        value = "",
                        label = "Username",
                        errorMessage = null
                    ),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                DialogTextField(
                    state = DialogTextFieldState(
                        value = "admin",
                        label = "Username",
                        errorMessage = null
                    ),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                DialogTextField(
                    state = DialogTextFieldState(
                        value = "",
                        label = "Username",
                        errorMessage = null
                    ),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                DialogTextField(
                    state = DialogTextFieldState(
                        value = "",
                        label = "Username",
                        errorMessage = "Username is empty!"
                    ),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                DialogTextField(
                    state = DialogTextFieldState(
                        value = "admin",
                        label = "Username",
                        errorMessage = "Username should contain digits!"
                    ),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
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
                    .padding(PaddingMedium),
                verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                DialogTextField(
                    state = usernameState,
                    onFieldValueChange = {
                        usernameState = usernameState.copy(value = it)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                DialogTextField(
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