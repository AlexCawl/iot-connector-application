package org.alexcawl.iot_connector.viewer.ui.screen.thermal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RangeSlider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import org.alexcawl.iot_connector.ui.components.HeatMap
import org.alexcawl.iot_connector.ui.components.toggle_button.MultiSelectorButton
import org.alexcawl.iot_connector.ui.state.data.ThermalRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.theme.extended.palettes.Palette
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import kotlin.random.Random

private const val DEFAULT_MIN_TEMP: Float = -40f
private const val DEFAULT_MAX_TEMP: Float = 200f
private val defaultRange: ClosedFloatingPointRange<Float> = DEFAULT_MIN_TEMP.rangeTo(DEFAULT_MAX_TEMP)

@Composable
fun ThermalViewerScreen(
    state: ThermalRepresentationModel,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier.padding(ExtendedTheme.padding.medium),
    verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    var selectedPaletteIndex: Int by remember { mutableIntStateOf(0) }
    val availablePalettes: List<Palette> = listOf(
        ExtendedTheme.palettes.iron,
        ExtendedTheme.palettes.gray,
        ExtendedTheme.palettes.rainbow
    )
    val selectedPalette: Palette = availablePalettes[selectedPaletteIndex]
    var range by remember { mutableStateOf(defaultRange) }

    MultiSelectorButton(
        onSelect = { selectedPaletteIndex = it },
        items = listOf(
            Pair("Iron", null),
            Pair("Gray", null),
            Pair("Rainbow", null)
        )
    )
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        HeatMap(
            values = state.temperatures,
            onColorPick = {
                if (it <= range.start) {
                    selectedPalette.minColor
                } else if (it >= range.endInclusive) {
                    selectedPalette.maxColor
                } else {
                    val step = (it - range.start) / (range.endInclusive - range.start)
                    selectedPalette.colors[(step * selectedPalette.length).toInt()]
                }
            },
            modifier = Modifier.blur(8.dp)
        )
    }

    RangeSlider(
        value = range,
        onValueChange = { range = it },
        valueRange = defaultRange,
        steps = 10
    )

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