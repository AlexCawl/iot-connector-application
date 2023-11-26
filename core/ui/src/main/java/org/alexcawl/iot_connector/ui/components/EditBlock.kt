package org.alexcawl.iot_connector.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

@Composable
fun EditBlock(
    title: @Composable () -> Unit,
    onChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
    errorMessage: (@Composable () -> Unit)? = null,
) {
    var editDialogActive: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.background(
            when (errorMessage) {
                null -> Color.Transparent
                else -> MaterialTheme.colorScheme.errorContainer
            }
        ),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        title()
        Row {
            Text(text = value)
            IconButton(onClick = { editDialogActive = editDialogActive.not() }) {
                Icon(imageVector = Icons.Filled.Build, contentDescription = null)
            }
        }
        errorMessage?.let { it() }
    }

//    EditActionDialogCaller(
//        onDismissRequest = { editDialogActive = false },
//        initialValue = value,
//        expanded = editDialogActive,
//        title = title,
//        onSubmit = onChange,
//    )
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    IoTConnectorTheme {
        Scaffold { paddingValues: PaddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                EditBlock(
                    title = {
                        Text(
                            text = "Edit username",
                            maxLines = 1,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    onChange = { username = it },
                    value = username,
                    errorMessage = when (username.length > 5) {
                        true -> {
                            { Text(text = "Username too big!") }
                        }
                        false -> null
                    }
                )
                EditBlock(
                    title = {
                        Text(
                            text = "Edit password",
                            maxLines = 1,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    onChange = { password = it },
                    value = password
                )
            }
        }
    }
}