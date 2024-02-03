package org.alexcawl.iot_connector.title.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.iot_connector.title.ui.TitleScreenAction
import org.alexcawl.iot_connector.title.ui.TitleScreenState

@Composable
fun TitleScreen(
    state: TitleScreenState,
    onAction: (TitleScreenAction) -> Unit,
    modifier: Modifier
) = Scaffold {
    Box(modifier = Modifier.padding(it))
}