package org.alexcawl.iot_connector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.network.client.MqttNetworkSource
import org.alexcawl.iot_connector.ui.theme.IoTConnectorTheme
import javax.inject.Inject

class DebugActivity : ComponentActivity() {
    @Inject
    lateinit var source: MqttNetworkSource

    private val state: MutableStateFlow<String> = MutableStateFlow("INIT")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as IoTConnectorApplication).applicationComponent.inject(this)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                source.subscribe("/sensor/amg8833/thermal").collect {
                    if (it.isSuccess) {
                        state.emit(it.getOrNull().toString())
                    }
                }
            }
        }

        setContent {
            IoTConnectorTheme {
                val screenState by state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(screenState)
                }
            }
        }
    }
}