package org.alexcawl.iot_connector.connection.dependencies

import androidx.lifecycle.ViewModelProvider
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import kotlinx.coroutines.flow.StateFlow
import org.alexcawl.iot_connector.common.util.Product
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao

interface ConnectionDependencies {
    val profileDatastoreDao: ProfileDatastoreDao
    val profileDatabaseDao: ProfileDatabaseDao
    val connectionDatabaseDao: ConnectionDatabaseDao
    val client: StateFlow<Product<Mqtt5AsyncClient>>
    val viewModelFactory: ViewModelProvider.Factory
}