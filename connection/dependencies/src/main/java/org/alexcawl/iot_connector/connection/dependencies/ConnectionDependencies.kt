package org.alexcawl.iot_connector.connection.dependencies

import androidx.lifecycle.ViewModelProvider
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import org.alexcawl.iot_connector.network.mqtt.IMqttClientHolder
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao

interface ConnectionDependencies {
    val connectionDatabaseDao: ConnectionDatabaseDao
    val holder: IMqttClientHolder<Mqtt5AsyncClient>
    val viewModelFactory: ViewModelProvider.Factory
}