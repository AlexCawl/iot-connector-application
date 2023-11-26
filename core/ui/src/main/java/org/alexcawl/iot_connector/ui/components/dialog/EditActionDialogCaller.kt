package org.alexcawl.iot_connector.ui.components.dialog

import android.content.res.Configuration
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

@Composable
fun EditActionDialogHolder(
    isShown: Boolean,
    title: @Composable () -> Unit,
    onSubmitRequest: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    initialText: String = ""
) {
    if (isShown) {
        EditActionDialog(title = title,
            content = { text: String, onChange: (String) -> Unit ->
                TextField(value = text, onValueChange = onChange)
            },
            onSubmitRequest = onSubmitRequest,
            confirmButton = { text: String, onConfirm: (String) -> Unit ->
                TextButton(onClick = { onConfirm(text) }) {
                    Text("Submit")
                }
            },
            onDismissRequest = onDismissRequest,
            dismissButton = { onDismiss: () -> Unit ->
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            },
            initialText = initialText,
            modifier = modifier
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    IoTConnectorTheme {
        var isShown by remember { mutableStateOf(true) }
        var text by remember { mutableStateOf("hello world!") }

        Button(onClick = { isShown = isShown.not() }) {
            Text(text = text)
        }

        EditActionDialogHolder(
            isShown = isShown,
            title = {
                Text(
                    text = "Edit username",
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            onSubmitRequest = {
                text = it
                isShown = false
            },
            onDismissRequest = { isShown = false },
            initialText = text
        )
    }
}

/*@Composable
fun EditActionDialogCaller(
    onDismissRequest: () -> Unit,
    expanded: Boolean,
    title: @Composable () -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier,
    initialValue: String = "",
) {
    var input: String by remember { mutableStateOf(initialValue) }
    val save: () -> Unit = {
        onSubmit(input)
        onDismissRequest()
    }
    val clear: () -> Unit = {
        input = initialValue
        onDismissRequest()
    }

    if (expanded) {
        AlertDialog(
            onDismissRequest = clear,
            title = title,
            text = {
                TextField(value = input, onValueChange = { input = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
            },
            confirmButton = {
                TextButton(onClick = save) { Text("Submit") }
            },
            dismissButton = {
                TextButton(onClick = clear) { Text("Cancel") }
            },
            modifier = modifier
        )
    }
}*/