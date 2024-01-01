package org.alexcawl.iot_connector.profile.ui.screen.add.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenState
import org.alexcawl.iot_connector.ui.components.LoadingPlaceholder
import org.alexcawl.iot_connector.ui.components.MaterialSpacer
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.PaddingSmall
import org.alexcawl.iot_connector.ui.components.Spacer
import org.alexcawl.iot_connector.ui.components.TextFieldEditBlock
import org.alexcawl.iot_connector.ui.components.TextFieldEditBlockState
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProfileScreen(
    state: AddProfileScreenState,
    onAction: (AddProfileScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Add profile",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = { onAction(AddProfileScreenAction.AddProfile) },
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
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues: PaddingValues ->
        val paddingModifier = Modifier.padding(paddingValues)
        when (state) {
            AddProfileScreenState.Initial -> LoadingPlaceholder(modifier = paddingModifier
                .fillMaxSize())
            is AddProfileScreenState.Building -> {
                Column(
                    modifier = paddingModifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Top),
                    horizontalAlignment = Alignment.Start
                ) {
                    var name by remember { mutableStateOf(state.name) }
                    val nameState = TextFieldEditBlockState(
                        value = name,
                        label = "Name",
                        errorMessage = state.nameMessage.toText()
                    )
                    TextFieldEditBlock(
                        state = nameState,
                        onFieldValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    var info by remember { mutableStateOf("") }
                    val infoState = TextFieldEditBlockState(value = info, label = "Info", optional = true)
                    TextFieldEditBlock(
                        state = infoState,
                        onFieldValueChange = { info = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    var host by remember { mutableStateOf("") }
                    val hostState = TextFieldEditBlockState(value = host, label = "Host")
                    TextFieldEditBlock(
                        state = hostState,
                        onFieldValueChange = { host = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    var port by remember { mutableStateOf("") }
                    val portState = TextFieldEditBlockState(value = port, label = "Port")
                    TextFieldEditBlock(
                        state = portState,
                        onFieldValueChange = { port = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    var login by remember { mutableStateOf("") }
                    val loginState = TextFieldEditBlockState(value = login, label = "Port")
                    TextFieldEditBlock(
                        state = loginState,
                        onFieldValueChange = { login = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                    MaterialSpacer()

                    var password by remember { mutableStateOf("") }
                    val passwordState = TextFieldEditBlockState(value = password, label = "Port")
                    TextFieldEditBlock(
                        state = passwordState,
                        onFieldValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PaddingMedium)
                    )
                }
            }
            AddProfileScreenState.Completed -> LoadingPlaceholder(
                modifier = paddingModifier.fillMaxSize(),
                title = {
                    Text(text = "Updating skeletons")
                }
            )
        }
    }
}

@Composable
fun AddProfileScreenState.Building.Message.toText(): String? = when (this) {
    AddProfileScreenState.Building.Message.OK -> null
    AddProfileScreenState.Building.Message.NULL -> stringResource(id = R.string.cannot_be_null)
    AddProfileScreenState.Building.Message.NOT_A_NUMBER -> stringResource(id = R.string.not_a_number)
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val state = AddProfileScreenState.Building()

    IoTConnectorTheme {
        AddProfileScreen(state = state, onAction = {}, onNavigateBack = { /*TODO*/ })
    }
}