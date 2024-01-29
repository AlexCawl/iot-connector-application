package org.alexcawl.iot_connector.profile.ui.screen.show.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import org.alexcawl.iot_connector.profile.R
import org.alexcawl.iot_connector.ui.components.IconSmall
import org.alexcawl.iot_connector.ui.components.CardScaffold
import org.alexcawl.iot_connector.ui.components.PaddingSmall
import org.alexcawl.iot_connector.ui.state.ProfileState
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import java.util.UUID

@Composable
internal fun SelectedProfileCard(
    profile: ProfileState?,
    onClick: (ProfileState?) -> Unit,
    modifier: Modifier = Modifier
) = CardScaffold(
    modifier = modifier,
    title = {
        Text(
            text = when (val text = profile?.name) {
                null -> stringResource(id = R.string.no_profiles_selected)
                else -> text
            },
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
    subtitle = {
        Text(
            text = when (profile) {
                null -> stringResource(id = R.string.select_profile_clicking)
                else -> when (val text = profile.info) {
                    null -> stringResource(id = R.string.no_description)
                    else -> text
                }
            },
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
    onClick = { onClick(profile) },
    statusIcon = {},
    configurationIcon = {
        when (profile) {
            null -> Unit
            else -> Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(IconSmall)
            )
        }
    },
    body = {}
)


@ThemedPreview
@Composable
private fun Preview() {
    val profiles = listOf(
        ProfileState(
            id = UUID.randomUUID(),
            name = "home",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8080,
            login = "user",
            password = "admin",
            info = "my home profile",
            changedAt = System.currentTimeMillis() + 1000L
        ), ProfileState(
            id = UUID.randomUUID(),
            name = "work",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8000,
            login = "user",
            info = "my work profile",
            changedAt = System.currentTimeMillis() + 5000L
        ), null, ProfileState(
            id = UUID.randomUUID(),
            name = "work",
            createdAt = System.currentTimeMillis(),
            host = "localhost",
            port = 8000,
            login = "user",
            changedAt = System.currentTimeMillis() + 5000L
        )
    )

    IoTConnectorTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(PaddingSmall, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            for (profile in profiles) {
                SelectedProfileCard(
                    profile = profile,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {})
            }
        }
    }
}