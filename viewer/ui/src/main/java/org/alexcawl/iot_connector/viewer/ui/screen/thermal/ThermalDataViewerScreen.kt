package org.alexcawl.iot_connector.viewer.ui.screen.thermal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.alexcawl.iot_connector.common.util.averageOrNull
import org.alexcawl.iot_connector.common.util.medianOrNull
import org.alexcawl.iot_connector.ui.components.card.InfoCard
import org.alexcawl.iot_connector.ui.state.data.ThermalDataRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.viewer.ui.R

@Composable
fun ThermalDataViewerScreen(
    state: ThermalDataRepresentationModel,
    modifier: Modifier = Modifier
) = Scaffold {
    LazyColumn(
        modifier = modifier
            .padding(it)
            .padding(ExtendedTheme.padding.medium),
        verticalArrangement = Arrangement.spacedBy(ExtendedTheme.padding.medium, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item(key = state.device) {
            InfoCard(
                key = stringResource(id = R.string.device),
                value = state.device,
                statusIcon = {}
            )
        }
        item(key = state.sensorType) {
            InfoCard(
                key = stringResource(id = R.string.sensor_type),
                value = state.sensorType,
                statusIcon = {}
            )
        }
        val minimum: Float? = state.temperatures.minOrNull()
        val maximum: Float? = state.temperatures.maxOrNull()
        item {
            InfoCard(
                key = stringResource(id = R.string.minimum),
                value = when (minimum) {
                    null -> stringResource(id = R.string.undefined)
                    else -> minimum.toString()
                },
                statusIcon = {}
            )
        }
        item {
            InfoCard(
                key = stringResource(id = R.string.maximum),
                value = when (maximum) {
                    null -> stringResource(id = R.string.undefined)
                    else -> maximum.toString()
                },
                statusIcon = {}
            )
        }

        val average: Float? = state.temperatures.averageOrNull()
        item {
            InfoCard(
                key = stringResource(id = R.string.average),
                value = when (average) {
                    null -> stringResource(id = R.string.undefined)
                    else -> average.toString()
                },
                statusIcon = {}
            )
        }

        val median: Float? = state.temperatures.medianOrNull()
        item {
            InfoCard(
                key = stringResource(id = R.string.median),
                value = when (median) {
                    null -> stringResource(id = R.string.undefined)
                    else -> median.toString()
                },
                statusIcon = {}
            )
        }
    }
}