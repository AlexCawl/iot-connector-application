package org.alexcawl.iot_connector.ui.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun RequiredDialogTextField(
    state: DialogTextFieldState,
    onFieldValueChange: (fieldValue: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = state.label,
            minLines = 1,
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        DialogTextField(
            state = state,
            onFieldValueChange = onFieldValueChange,
            modifier = Modifier.fillMaxWidth()
        )
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
                    .padding(PaddingMedium),
                verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = "admin",
                        label = "Username",
                        errorMessage = "Username should contain digits!"
                    ),
                    onFieldValueChange = {}
                )
                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = "",
                        label = "Username",
                    ),
                    onFieldValueChange = {}
                )
                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = "admin",
                        label = "Username",
                        errorMessage = "Username should contain digits!"
                    ),
                    onFieldValueChange = {}
                )
                RequiredDialogTextField(
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
                RequiredDialogTextField(
                    state = usernameState,
                    onFieldValueChange = { usernameState = usernameState.copy(value = it) },
                    modifier = Modifier.fillMaxWidth()
                )

                RequiredDialogTextField(
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