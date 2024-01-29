package org.alexcawl.iot_connector.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScaffold(
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    onClick: () -> Unit,
    statusIcon: @Composable () -> Unit,
    configurationIcon: @Composable () -> Unit,
    body: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = Card(
    onClick = onClick, modifier = modifier
) {
    ItemCardContent(
        title = title,
        subtitle = subtitle,
        statusIcon = statusIcon,
        configurationIcon = configurationIcon,
        body = body
    )
}

@Composable
fun CardScaffold(
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    statusIcon: @Composable () -> Unit,
    configurationIcon: @Composable () -> Unit,
    body: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = Card(
    modifier = modifier
) {
    ItemCardContent(
        title = title,
        subtitle = subtitle,
        statusIcon = statusIcon,
        configurationIcon = configurationIcon,
        body = body
    )
}

@Composable
internal fun ItemCardContent(
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    statusIcon: @Composable () -> Unit,
    configurationIcon: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.padding(PaddingMedium),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            statusIcon()
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(PaddingSmall, Alignment.Top),
                horizontalAlignment = Alignment.Start
            ) {
                title()
                subtitle()
            }
            configurationIcon()
        }
        body()
    }
}

@ThemedPreview
@Composable
private fun Preview() {
    IoTConnectorTheme {
        Scaffold {
            Column(modifier = Modifier
                .padding(it)
                .padding(PaddingMedium)) {
                CardScaffold(
                    title = {
                        Text(
                            text = loremIpsum(3),
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    subtitle = {
                        Text(
                            text = loremIpsum(5),
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onClick = {},
                    statusIcon = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(IconMedium)
                        )
                    },
                    configurationIcon = {
                        Box(modifier = Modifier.fillMaxHeight()) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier.size(IconLittle)
                            )
                        }
                    },
                    body = {
                        Spacer(modifier = Modifier.height(PaddingMedium))
                        Text(
                            text = loremIpsum(2),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
    }
}