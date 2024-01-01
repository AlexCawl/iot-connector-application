package org.alexcawl.iot_connector.profile.ui.screen.show.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.toDateFormat
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileItem(
    profile: Profile,
    onClicked: (Profile) -> Unit,
    onEditClicked: (Profile) -> Unit,
    modifier: Modifier = Modifier,
    showType: ProfileShowType = ProfileShowType.NONE
) {
    Card(
        modifier = modifier,
        onClick = { onClicked(profile) },
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier.padding(PaddingMedium),
            verticalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
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
                        overflow = TextOverflow.Ellipsis
                    )
                    profile.info?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                IconButton(
                    onClick = { onEditClicked(profile) },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(IntrinsicSize.Max)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings, contentDescription = null,
                        modifier = Modifier.height(IntrinsicSize.Max)
                    )
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = "${profile.host}:${profile.port}",
                style = MaterialTheme.typography.titleMedium,
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
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

enum class ProfileShowType {
    NONE,
    CREATED_AT,
    CHANGED_AT
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val profile = Profile(
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
            ProfileItem(profile = profile, onClicked = {}, onEditClicked = {}, showType = ProfileShowType.CHANGED_AT)
        }
    }
}