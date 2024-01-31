package org.alexcawl.iot_connector.connection.ui.screen.update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.connection.R
import org.alexcawl.iot_connector.ui.components.Spacer
import org.alexcawl.iot_connector.ui.components.input.DialogTextFieldState
import org.alexcawl.iot_connector.ui.components.input.OptionalDialogTextField
import org.alexcawl.iot_connector.ui.components.input.RequiredDialogTextField
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateConnectionScreen(
    state: UpdateConnectionScreenState,
    onAction: (UpdateConnectionScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    title: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        CenterAlignedTopAppBar(
            title = title,
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                TextButton(
                    onClick = { onAction(UpdateConnectionScreenAction.Save) },
                    shape = MaterialTheme.shapes.small,
                ) {
                    Text(
                        text = stringResource(id = R.string.save).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )
    },
    floatingActionButton = floatingActionButton
) { paddingValues ->
    val paddingModifier = Modifier.padding(paddingValues)
    when (state) {
        is UpdateConnectionScreenState.Initial -> LoadingScreen(
            modifier = paddingModifier.fillMaxSize()
        )

        is UpdateConnectionScreenState.Builder -> {
            Column(
                modifier = paddingModifier
                    .verticalScroll(rememberScrollState())
                    .padding(ExtendedTheme.padding.medium),
                verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                RequiredDialogTextField(
                    state = DialogTextFieldState(
                        value = state.endpoint,
                        label = stringResource(id = R.string.connection_endpoint),
                        errorMessage = state.endpointMessage.toText()
                    ),
                    onFieldValueChange = { onAction(UpdateConnectionScreenAction.SetEndpoint(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OptionalDialogTextField(
                    visible = state.nameOptional.not(),
                    onVisibilityChange = { onAction(UpdateConnectionScreenAction.SetNameType(it.not())) },
                    state = DialogTextFieldState(
                        value = state.name,
                        label = stringResource(id = R.string.connection_name)
                    ),
                    onFieldValueChange = { onAction(UpdateConnectionScreenAction.SetName(it)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(ExtendedTheme.padding.large * 2)
                        .background(color = Color.Transparent)
                )
            }
        }

        is UpdateConnectionScreenState.Saving -> LaunchedEffect(key1 = null) {
            onNavigateBack()
        }
    }
}

@Composable
private fun UpdateConnectionScreenState.Builder.Message.toText(): String? = when (this) {
    UpdateConnectionScreenState.Builder.Message.OK -> null
    UpdateConnectionScreenState.Builder.Message.EMPTY -> stringResource(id = R.string.endpoint_empty)
    UpdateConnectionScreenState.Builder.Message.WRONG_PATTERN -> stringResource(id = R.string.endpoint_wrong_pattern)
}

@Composable
@ThemedPreview
private fun Preview() {
    val state = UpdateConnectionScreenState.Builder()

    IoTConnectorTheme {
        UpdateConnectionScreen(
            state = state,
            onAction = {},
            onNavigateBack = {},
            title = {},
            floatingActionButton = {}
        )
    }
}