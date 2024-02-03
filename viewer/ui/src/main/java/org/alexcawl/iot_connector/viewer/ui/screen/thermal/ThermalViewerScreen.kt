package org.alexcawl.iot_connector.viewer.ui.screen.thermal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.alexcawl.iot_connector.ui.components.BoxWithZoom
import org.alexcawl.iot_connector.ui.components.HeatMap
import org.alexcawl.iot_connector.ui.components.InfoCard
import org.alexcawl.iot_connector.ui.state.data.ThermalRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.theme.extended.palettes.Palette
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import kotlin.random.Random

@Composable
fun ThermalViewerScreen(
    state: ThermalRepresentationModel,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier.padding(ExtendedTheme.padding.medium),
    verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoCard(
            key = "Device",
            value = state.device,
            statusIcon = {},
            modifier = Modifier.weight(1f)
        )
        InfoCard(
            key = "Sensor type",
            value = state.sensorType,
            statusIcon = {},
            modifier = Modifier.weight(1f)
        )
    }

    BoxWithZoom(
        content = {
            HeatMap(
                values = state.temperatures,
                onColorPick = thermalColorPicker(ExtendedTheme.palettes.iron),
                modifier = it.blur(4.dp)
            )
        },
        modifier = Modifier.weight(1f)
    )
//    BoxWithConstraints(
//        modifier = Modifier.weight(1f),
//        contentAlignment = Alignment.Center
//    ) {
//        HeatMap(
//            values = state.temperatures,
//            onColorPick = thermalColorPicker(ExtendedTheme.palettes.iron),
//            modifier = Modifier.blur(4.dp)
//        )
//    }

    val temperatures = state.temperatures.flatMap { it.asIterable() }
    Row(
        horizontalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoCard(
            key = "Min",
            value = "${temperatures.minOrNull() ?: "-"}",
            statusIcon = {},
            modifier = Modifier.weight(1f)
        )
        InfoCard(
            key = "Avg",
            value = "${temperatures.average()}",
            statusIcon = {},
            modifier = Modifier.weight(1f)
        )
        InfoCard(
            key = "Max",
            value = "${temperatures.maxOrNull() ?: "-"}",
            statusIcon = {},
            modifier = Modifier.weight(1f)
        )
    }
}

private val thermalColorPicker: (Palette) -> ((Float) -> Color) = { palette ->
    val minTemp = 10
    val maxTemp = 40

    {
        if (it <= minTemp) {
            palette.minColor
        } else if (it >= maxTemp) {
            palette.maxColor
        } else {
            val step: Float = (it - minTemp) / (maxTemp - minTemp)
            palette.colors[(step * palette.length).toInt()]
        }
    }
}


@Composable
@ThemedPreview
private fun Preview() {
    val temperatures = Array(32) { Array(24) { Random.nextInt(-30, 250).toFloat() } }
    val state = ThermalRepresentationModel(
        device = "esp32",
        sensorType = "stable",
        temperatures = temperatures
    )
    IoTConnectorTheme {
        ThermalViewerScreen(state = state)
    }
}