package org.alexcawl.iot_connector.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
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
fun <T> EditDialog(
    onDismissRequest: () -> Unit,
    expanded: Boolean,
    initialValue: T,
    title: @Composable () -> Unit,
    onSubmit: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (value: T, onValueChange: (T) -> Unit) -> Unit
) {
    var input: T by remember { mutableStateOf(initialValue) }
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
            text = { content(input) { input = it } },
            confirmButton = {
                TextButton(onClick = save) { Text("Submit") }
            },
            dismissButton = {
                TextButton(onClick = clear) { Text("Cancel") }
            },
            modifier = modifier
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    IoTConnectorTheme {
        var expanded by remember { mutableStateOf(false) }
        var input by remember { mutableStateOf("") }

        Column {
            Button(onClick = { expanded = expanded.not() }) {
                Text(text = "Change state")
            }
            Text(text = input)
        }

        EditDialog(
            onDismissRequest = { expanded = false },
            initialValue = input,
            expanded = expanded,
            title = {
                Text(
                    text = "Edit username",
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            onSubmit = { input = it },
        ) { value: String, onValueChange: (String) -> Unit ->
            TextField(value = value, onValueChange = onValueChange)
        }
    }
}