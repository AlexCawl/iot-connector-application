package org.alexcawl.iot_connector.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.ui.R
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

@Composable
fun <T> InputSelector(
    title: String,
    initial: T,
    onSubmit: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (value: T, onValueChange: (T) -> Unit) -> Unit
) {
    var input: T by remember { mutableStateOf(initial) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title, maxLines = 1, style = MaterialTheme.typography.titleLarge
        )
        content(input) { input = it }
        Button(
            onClick = { onSubmit(input) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingLarge)
        ) {
            Text(
                text = stringResource(id = R.string.submit),
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val sheetState = SheetState(false, SheetValue.Expanded)
    val scaffoldState = rememberBottomSheetScaffoldState(sheetState)
    IoTConnectorTheme {
        BottomSheetScaffold(
            sheetContent = {
                InputSelector(
                    title = "Select username:",
                    initial = "admin",
                    onSubmit = {},
                    modifier = Modifier.padding(PaddingMedium)
                ) { value: String, onValueChange: (String) -> Unit ->
                    TextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(text = "Hi")
        }
    }
}