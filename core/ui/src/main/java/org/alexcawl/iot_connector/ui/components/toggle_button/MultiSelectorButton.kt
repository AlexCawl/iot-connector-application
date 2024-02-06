package org.alexcawl.iot_connector.ui.components.toggle_button

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun MultiSelectorButton(
    onSelect: (Int) -> Unit,
    items: List<Pair<String, ImageVector?>>,
    modifier: Modifier = Modifier
) = MultiSelectorButtonScaffold(modifier = modifier) {
    var selected: Int by remember { mutableIntStateOf(items.indices.first) }
    items.forEachIndexed { index: Int, (label: String, icon: ImageVector?) ->
        MultiSelectorButtonCell(
            selected = index == selected,
            onClick = {
                selected = index
                onSelect(selected)
            },
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = when (index == selected) {
                        true -> MaterialTheme.colorScheme.onPrimary
                        false -> MaterialTheme.colorScheme.onSurface
                    }
                )
            },
            icon = {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = when (index == selected) {
                            true -> MaterialTheme.colorScheme.onPrimary
                            false -> MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }
        )
    }
}


@Composable
internal fun MultiSelectorButtonScaffold(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) = Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .height(IntrinsicSize.Max),
    content = content
)

@Composable
internal fun RowScope.MultiSelectorButtonCell(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) = AnimatedContent(targetState = selected, label = "", modifier = Modifier.weight(1f)) { selectedState ->
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .let {
                when {
                    selectedState -> it.background(MaterialTheme.colorScheme.primary)
                    else -> it
                }
            }
            .padding(ExtendedTheme.padding.small),
        verticalArrangement = Arrangement.spacedBy(
            ExtendedTheme.padding.small,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        label()
        icon()
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    IoTConnectorTheme {
        MultiSelectorButton(
            onSelect = {},
            items = listOf(
                Pair("Lorem", Icons.Default.AccountBox),
                Pair("Ipsum", Icons.Default.AccountCircle),
                Pair("Dolor", Icons.Default.Search)
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}