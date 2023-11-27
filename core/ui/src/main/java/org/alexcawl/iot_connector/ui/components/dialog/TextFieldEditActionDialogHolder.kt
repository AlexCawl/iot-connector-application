package org.alexcawl.iot_connector.ui.components.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.ui.components.loremIpsum
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

@Composable
fun EditActionDialogHolder(
    isShown: Boolean,
    initialValue: String,
    title: @Composable () -> Unit,
    onSubmitRequest: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isShown) {
        EditActionDialog(title = title,
            content = { text: String, onChange: (String) -> Unit ->
                TextField(
                    value = text,
                    onValueChange = onChange,
                    textStyle = MaterialTheme.typography.bodyLarge,
                    minLines = 1,
                    maxLines = 8
                )
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
            initialValue = initialValue,
            modifier = modifier
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StaticPreview() {
    IoTConnectorTheme {
        EditActionDialogHolder(
            isShown = true,
            title = {
                Text(
                    text = loremIpsum(20),
                    minLines = 1,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis
                )
            },
            onSubmitRequest = {},
            onDismissRequest = {},
            initialValue = loremIpsum(20)
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DynamicPreview() {
    IoTConnectorTheme {
        var isShown by remember { mutableStateOf(true) }
        var text by remember { mutableStateOf(loremIpsum(500)) }

        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Press the button and modify text inside",
                    maxLines = 1
                )
                Button(onClick = { isShown = isShown.not() }) {
                    Text(
                        text = text,
                        maxLines = 1
                    )
                }
            }
        }

        EditActionDialogHolder(
            isShown = isShown,
            title = {
                Text(
                    text = loremIpsum(20),
                    minLines = 1,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis
                )
            },
            onSubmitRequest = {
                text = it
                isShown = false
            },
            onDismissRequest = { isShown = false },
            initialValue = text
        )
    }
}