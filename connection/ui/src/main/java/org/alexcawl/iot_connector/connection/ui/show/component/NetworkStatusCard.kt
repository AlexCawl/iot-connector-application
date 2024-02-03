package org.alexcawl.iot_connector.connection.ui.show.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.connection.ui.R
import org.alexcawl.iot_connector.ui.components.CardScaffold
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun NetworkStatusCard(
    isNetworkAvailable: Result<Boolean>, modifier: Modifier = Modifier
) {
    val type: NetworkStatusIconType = isNetworkAvailable.toNetworkStatus()
    val isError: Boolean = (type == NetworkStatusIconType.EXCEPTION)
    var isShown: Boolean by remember { mutableStateOf(false) }

    CardScaffold(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.mqtt_network_card_title),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        subtitle = {
            Text(
                text = when (type) {
                    NetworkStatusIconType.LOADING -> stringResource(id = R.string.network_type_loading)
                    NetworkStatusIconType.OK -> stringResource(id = R.string.network_type_ok)
                    NetworkStatusIconType.EXCEPTION -> stringResource(id = R.string.network_type_error)
                },
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        statusIcon = {
            when (type) {
                NetworkStatusIconType.LOADING -> Icon(
                    imageVector = Icons.Default.Refresh, contentDescription = null
                )

                NetworkStatusIconType.OK -> Icon(
                    imageVector = Icons.Default.CheckCircle, contentDescription = null
                )

                NetworkStatusIconType.EXCEPTION -> Icon(
                    imageVector = Icons.Default.Warning, contentDescription = null
                )
            }
        },
        configurationIcon = {
            if (isError) {
                val rotationState by animateFloatAsState(
                    targetValue = if (isShown) 180f else 0f,
                    label = VISIBLE_ICON_LABEL
                )

                IconButton(
                    onClick = { isShown = isShown.not() }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.rotate(rotationState)
                    )
                }
            }
        },
        body = {
            val stackTrace = isNetworkAvailable.exceptionOrNull()
            val visible = isError && isShown && stackTrace != null
            AnimatedVisibility(visible = visible) {
                if (stackTrace != null) {
                    Column {
                        Spacer(modifier = Modifier.height(ExtendedTheme.padding.medium))
                        Text(
                            text = stackTrace.stackTraceToString(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .height(ExtendedTheme.size.large)
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.surface)
                                .verticalScroll(state = rememberScrollState())
                                .padding(ExtendedTheme.padding.small)
                        )
                    }
                }
            }
        }
    )
}

private fun Result<Boolean>.toNetworkStatus(): NetworkStatusIconType =
    when (val availability = this.getOrNull()) {
        null -> NetworkStatusIconType.EXCEPTION
        else -> when (availability) {
            true -> NetworkStatusIconType.OK
            false -> NetworkStatusIconType.LOADING
        }
    }

private enum class NetworkStatusIconType {
    LOADING, OK, EXCEPTION
}

private const val VISIBLE_ICON_LABEL: String = "VISIBLE_ICON_LABEL"

@Composable
@ThemedPreview
private fun LoadingPreview() {
    val loadingState: Result<Boolean> = Result.success(false)
    val okState: Result<Boolean> = Result.success(true)
    val errorState: Result<Boolean> = Result.failure(IllegalStateException())

    IoTConnectorTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top)
        ) {
            NetworkStatusCard(isNetworkAvailable = loadingState)
            NetworkStatusCard(isNetworkAvailable = okState)
            NetworkStatusCard(isNetworkAvailable = errorState)
        }
    }
}