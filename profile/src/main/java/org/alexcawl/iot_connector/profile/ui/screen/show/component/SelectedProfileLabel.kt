package org.alexcawl.iot_connector.profile.ui.screen.show.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.PaddingSmall
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import java.util.UUID

@Composable
internal fun SelectedProfileLabel(
    profile: Profile?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(PaddingMedium),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start
    ) {
        when (profile) {
            null -> {
                Text(
                    text = stringResource(id = R.string.no_profiles_selected),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            else -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = profile.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        profile.info?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = null,
                        modifier = Modifier.fillMaxSize().padding(PaddingSmall),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
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
            host = "localhost",
            port = 8080,
            login = "user",
            password = "admin",
            info = "my home profile",
            changedAt = System.currentTimeMillis() + 1000L
        ),
        Profile(
            id = UUID.randomUUID(),
            name = "work",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8000,
            login = "user",
            info = "my work profile",
            changedAt = System.currentTimeMillis() + 5000L
        ),
        null
    )

    IoTConnectorTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(PaddingSmall, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            for (profile in profiles) {
                SelectedProfileLabel(profile = profile, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}