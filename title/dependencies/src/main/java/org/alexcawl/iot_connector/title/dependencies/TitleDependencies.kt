package org.alexcawl.iot_connector.title.dependencies

import androidx.lifecycle.ViewModelProvider
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import org.alexcawl.iot_connector.network.mqtt.IMqttClientHolder
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao

interface TitleDependencies {
    val profileDatabaseDao: ProfileDatabaseDao
    val profileDatastoreDao: ProfileDatastoreDao
    val clientHolder: IMqttClientHolder<Mqtt5AsyncClient>
    val viewModelFactory: ViewModelProvider.Factory
}