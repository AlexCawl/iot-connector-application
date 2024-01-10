package org.alexcawl.iot_connector.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.alexcawl.iot_connector.ui.util.ThemedPreview

@Composable
fun RowScope.Spacer(modifier: Modifier = Modifier) = Box(modifier = modifier.fillMaxHeight())

@Composable
fun ColumnScope.Spacer(modifier: Modifier = Modifier) = Box(modifier = modifier.fillMaxWidth())

@Composable
fun ColumnScope.MaterialSpacer() = Spacer(modifier = Modifier
    .height(1.dp)
    .background(color = MaterialTheme.colorScheme.outline))

@ThemedPreview
@Composable
private fun Preview() {
    LazyColumn {
        item {
            LazyRow {
                item {
                    Box(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}