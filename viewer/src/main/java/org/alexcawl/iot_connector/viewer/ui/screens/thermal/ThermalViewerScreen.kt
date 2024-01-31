package org.alexcawl.iot_connector.viewer.ui.screens.thermal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.alexcawl.iot_connector.ui.components.HeatMap
import org.alexcawl.iot_connector.ui.components.InfoCard
import org.alexcawl.iot_connector.ui.state.data.ThermalRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
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

    BoxWithConstraints(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center
    ) {
        HeatMap(
            values = state.temperatures,
            onColorPick = { if (it % 2 == 0) Color.Green else Color.Red }
        )
    }

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

@Composable
@ThemedPreview
private fun Preview() {
    val temperatures = Array(32) { Array(24) { Random.nextInt(-30, 250) } }
    val state = ThermalRepresentationModel(
        device = "esp32",
        sensorType = "stable",
        temperatures = temperatures
    )
    IoTConnectorTheme {
        ThermalViewerScreen(state = state)
    }
}