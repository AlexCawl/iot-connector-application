package org.alexcawl.iot_connector.profile.ui.screen.add.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenAction
import org.alexcawl.iot_connector.profile.ui.screen.add.AddProfileScreenState

@Composable
fun AddProfileScreen(
    state: AddProfileScreenState,
    action: (AddProfileScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview(name = "Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {

}