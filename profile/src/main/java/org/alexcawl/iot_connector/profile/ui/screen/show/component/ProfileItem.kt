package org.alexcawl.iot_connector.profile.ui.screen.show.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
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
import org.alexcawl.iot_connector.common.model.MQTTConfiguration
import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.ui.components.IconLarge
import org.alexcawl.iot_connector.ui.components.PaddingMedium
import org.alexcawl.iot_connector.ui.components.PaddingSmall
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import org.alexcawl.iot_connector.ui.util.toDateFormat
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileItem(
    profile: Profile,
    selected: Boolean,
    onClicked: (Profile) -> Unit,
    onEditClicked: (Profile) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = { onClicked(profile) },
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier.padding(PaddingMedium),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(PaddingMedium, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = when (selected) {
                        true -> Icons.Default.CheckCircle
                        false -> Icons.Default.AccountCircle
                    },
                    contentDescription = null,
                    tint = when (selected) {
                        true -> MaterialTheme.colorScheme.primary
                        false -> MaterialTheme.colorScheme.secondary
                    },
                    modifier = Modifier
                        .size(IconLarge)
                        .padding(PaddingSmall)
                )
                Column(
                    modifier = Modifier.weight(1f),
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
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    IconButton(onClick = { onEditClicked(profile) }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            }

            val date = profile.changedAt ?: profile.createdAt
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                text = date.toDateFormat(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    val profile = Profile(
        id = UUID.randomUUID(),
        name = "home",
        createdAt = System.currentTimeMillis(),
        configuration = MQTTConfiguration(
            host = "localhost", port = 8080, login = "user", password = "admin"
        ),
        info = "my home profile"
    )
    val selected = true

    IoTConnectorTheme {
        Box(Modifier.fillMaxWidth()) {
            ProfileItem(profile = profile, selected = selected, onClicked = {}, onEditClicked = {})
        }
    }
}