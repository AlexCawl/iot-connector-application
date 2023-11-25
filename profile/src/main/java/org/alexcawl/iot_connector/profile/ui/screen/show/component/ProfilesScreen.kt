package org.alexcawl.iot_connector.profile.ui.screen.show.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.common.model.MQTTConfiguration
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.ui.components.PaddingSmall
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import java.util.UUID

@Composable
internal fun AllProfilesScreenContent(
    profiles: List<Profile>,
    selected: UUID?,
    onSelectProfile: (UUID) -> Unit,
    onEditProfile: (UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(PaddingSmall),
        verticalArrangement = Arrangement.spacedBy(PaddingSmall, Alignment.Top)
    ) {
        items(profiles) {
            ProfileItem(
                profile = it,
                selected = it.id == selected,
                onClicked = { profile -> onSelectProfile(profile.id) },
                onEditClicked = { profile -> onEditProfile(profile.id) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val profiles = listOf(
        Profile(
            id = UUID.randomUUID(),
            name = "home",
            createdAt = System.currentTimeMillis(),
            configuration = MQTTConfiguration(
                host = "localhost", port = 8080, login = "user", password = "admin"
            ),
            info = "my home profile",
            changedAt = System.currentTimeMillis() + 1000L
        ), Profile(
            id = UUID.randomUUID(),
            name = "work",
            createdAt = System.currentTimeMillis(),
            configuration = MQTTConfiguration(
                host = "localhost", port = 8000, login = "user"
            ),
            info = "my work profile",
            changedAt = System.currentTimeMillis() + 5000L
        ), Profile(
            id = UUID.randomUUID(),
            name = "default",
            createdAt = System.currentTimeMillis(),
            configuration = MQTTConfiguration(
                host = "localhost", port = 8000
            )
        )
    )
    val selected: UUID = profiles.first().id

    IoTConnectorTheme {
        Box(Modifier.fillMaxSize()) {
            AllProfilesScreenContent(
                profiles = profiles,
                selected = selected,
                onSelectProfile = {},
                onEditProfile = {}
            )
        }
    }
}