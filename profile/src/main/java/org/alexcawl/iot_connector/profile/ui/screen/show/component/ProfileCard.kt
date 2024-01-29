package org.alexcawl.iot_connector.profile.ui.screen.show.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.components.IconSmall
import org.alexcawl.iot_connector.ui.components.CardScaffold
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.PaddingSmall
import org.alexcawl.iot_connector.ui.state.ProfileState
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.ThemedPreview
import org.alexcawl.iot_connector.ui.util.toDateFormat
import java.util.UUID

@Composable
internal fun ProfileCard(
    profile: ProfileState,
    onClicked: (UUID) -> Unit,
    onEditClicked: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    showType: ProfileShowType = ProfileShowType.NONE
) = CardScaffold(
    modifier = modifier,
    title = {
        Text(
            text = profile.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
    subtitle = {
        profile.info?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    },
    onClick = { onClicked(profile.id) },
    statusIcon = {},
    configurationIcon = {
        IconButton(
            onClick = { onEditClicked(profile.id) }, modifier = Modifier.fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(IconSmall)
            )
        }
    },
    body = {
        Spacer(modifier = Modifier.height(PaddingMedium))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(PaddingSmall, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = "${profile.host}:${profile.port}",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            when (showType) {
                ProfileShowType.NONE -> null
                ProfileShowType.CREATED_AT -> profile.createdAt
                ProfileShowType.CHANGED_AT -> profile.changedAt ?: profile.createdAt
            }?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = it.toDateFormat(),
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
)

enum class ProfileShowType {
    NONE, CREATED_AT, CHANGED_AT
}

@ThemedPreview
@Composable
private fun Preview() {
    val profile = ProfileState(
        id = UUID.randomUUID(),
        name = "home",
        createdAt = System.currentTimeMillis(),
        host = "localhost",
        port = 8080,
        login = "user",
        password = "admin",
        info = "my home profile"
    )

    IoTConnectorTheme {
        Box(Modifier.fillMaxWidth()) {
            ProfileCard(
                profile = profile,
                onClicked = {},
                onEditClicked = {},
                showType = ProfileShowType.CHANGED_AT
            )
        }
    }
}