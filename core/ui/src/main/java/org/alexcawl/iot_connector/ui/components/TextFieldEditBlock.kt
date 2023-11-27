package org.alexcawl.iot_connector.ui.components

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.ui.components.dialog.EditActionDialogHolder
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme


@Composable
fun TextFieldEditBlock(
    state: FieldEditBlockState,
    onFieldValueChange: (fieldValue: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDialogShown: Boolean by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = state.value,
        onValueChange = {},
        readOnly = true,
        textStyle = MaterialTheme.typography.bodyLarge,
        minLines = 1,
        maxLines = 4,
        modifier = modifier,
        label = {
            when (state.showLabel) {
                true -> Text(text = state.label)
                false -> Unit
            }
        },
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
        }
    )

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
data class FieldEditBlockState(
    val value: String,
    val label: String,
    val showLabel: Boolean = true,
    val errorMessage: String? = null,
)

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
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
                TextFieldEditBlock(
                    state = FieldEditBlockState("", "Username", true, null),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = FieldEditBlockState("admin", "Username", true, null),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = FieldEditBlockState("", "Username", false, null),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = FieldEditBlockState("admin", "Username", false, null),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = FieldEditBlockState("", "Username", true, "Username is empty!"),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = FieldEditBlockState("admin", "Username", false, "Username should contain digits!"),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = FieldEditBlockState(loremIpsum(500), "Username", true, null),
                    onFieldValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DynamicPreview() {
    var usernameState: FieldEditBlockState by remember {
        mutableStateOf(FieldEditBlockState("", "Select username:", true, null))
    }
    var passwordState: FieldEditBlockState by remember {
        mutableStateOf(FieldEditBlockState("1234", "Select password:", true, "Password is too weak!"))
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
                TextFieldEditBlock(
                    state = usernameState, onFieldValueChange = {
                        usernameState = usernameState.copy(value = it)
                    }, modifier = Modifier.fillMaxWidth()
                )
                TextFieldEditBlock(
                    state = passwordState, onFieldValueChange = {
                        passwordState = passwordState.copy(value = it, errorMessage = if (it.length < 5) "Password is too weak!" else null)
                    }, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}