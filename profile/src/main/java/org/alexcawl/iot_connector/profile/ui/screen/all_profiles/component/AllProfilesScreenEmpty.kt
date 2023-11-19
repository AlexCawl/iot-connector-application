package org.alexcawl.iot_connector.profile.ui.screen.all_profiles.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

@Composable
internal fun AllProfilesScreenEmpty(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You have no profiles",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Add them using button below!",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    IoTConnectorTheme {
        Box(Modifier.fillMaxSize()) {
            AllProfilesScreenEmpty(modifier = Modifier.fillMaxSize())
        }
    }
}