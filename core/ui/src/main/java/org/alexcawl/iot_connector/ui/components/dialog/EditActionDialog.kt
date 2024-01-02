package org.alexcawl.iot_connector.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun EditActionDialog(
    initialValue: String,
    title: @Composable () -> Unit,
    content: @Composable (text: String, onChange: (String) -> Unit) -> Unit,
    onSubmitRequest: (String) -> Unit,
    confirmButton: @Composable (text: String, onConfirm: (String) -> Unit) -> Unit,
    onDismissRequest: () -> Unit,
    dismissButton: @Composable (onDismiss: () -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    var text: String by remember { mutableStateOf(initialValue) }
    val save: (String) -> Unit = {
        onSubmitRequest(it)
        onDismissRequest()
    }
    val clear: () -> Unit = {
        text = initialValue
        onDismissRequest()
    }

    ActionDialog(
        title = title,
        content = { content(text) { text = it } },
        confirmButton = { confirmButton(text, save) },
        dismissButton = { dismissButton(clear) },
        onDismissRequest = onDismissRequest,
        modifier = modifier
    )
}