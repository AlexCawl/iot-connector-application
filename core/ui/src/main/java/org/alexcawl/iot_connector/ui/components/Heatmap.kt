package org.alexcawl.iot_connector.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import kotlin.random.Random

@Composable
fun <T> HeatMap(
    values: Array<Array<T>>,
    onColorPick: (T) -> Color,
    modifier: Modifier = Modifier,
    maintainAspectRatio: Boolean = true
) {
    val countOfRows = values.size
    val countOfColumns = values.maxOfOrNull { it.size } ?: 0
    require(countOfRows != 0 && countOfColumns != 0)
    Canvas(
        modifier = modifier.defaultMinSize(
            minWidth = countOfColumns.dp,
            minHeight = countOfRows.dp
        ).let {
            if (maintainAspectRatio) {
                it.aspectRatio(countOfColumns.toFloat() / countOfRows.toFloat())
            } else {
                it
            }
        }
    ) {
        val itemSize = Size(size.width / countOfColumns, size.height / countOfRows)
        values.forEachIndexed { iRow, row ->
            row.forEachIndexed { jColumn, item ->
                drawRect(
                    color = onColorPick(item),
                    topLeft = Offset(itemSize.width * jColumn, itemSize.height * iRow),
                    size = itemSize
                )
            }
        }
    }
}

@Composable
@ThemedPreview
private fun Preview() {
    val (x, y) = Pair(32, 24)
    val temperatures = Array(x) { Array(y) { Random.nextDouble(-30.0, 250.0).toFloat() } }
    val picker = { temp: Float ->
        when (temp.toInt() % 2 == 0) {
            true -> Color.Red
            false -> Color.Green
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        HeatMap(
            values = temperatures,
            onColorPick = picker,
            modifier = Modifier
                .blur(2.dp)
        )
    }
}