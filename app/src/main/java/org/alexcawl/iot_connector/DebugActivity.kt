package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme

class DebugActivity : ComponentActivity() {
    private val network: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    private val content: MutableStateFlow<String> = MutableStateFlow("INIT")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as IoTConnectorApplication).applicationComponent.inject(this)

        setContent {
            IoTConnectorTheme {
                val networkState by network.collectAsState()
                val payloadState by content.collectAsState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(networkState.toString())
                    Text(payloadState)
                }
            }
        }
    }
}