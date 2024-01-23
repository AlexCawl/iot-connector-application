package org.alexcawl.iot_connector.di.module

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import dagger.Module
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.exceptions.ProfileNotSelectedException
import org.alexcawl.iot_connector.common.model.Login
import org.alexcawl.iot_connector.common.model.Password
import org.alexcawl.iot_connector.common.util.Product
import org.alexcawl.iot_connector.common.util.runProducing
import org.alexcawl.iot_connector.network.mqtt.IMqttClientActivator
import org.alexcawl.iot_connector.network.mqtt.IMqttClientFactory
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientActivator
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientFactory
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import java.util.UUID

@Module
interface NetworkModule {
    fun bindMqttFactory(asyncFactory: MqttAsyncClientFactory): IMqttClientFactory<Mqtt5AsyncClient>

    fun bindMqttActivator(activator: MqttAsyncClientActivator): IMqttClientActivator<Mqtt5AsyncClient>

    companion object {
        fun provideMqttAsyncClientFlow(
            profileDatabaseDao: ProfileDatabaseDao,
            profileDatastoreDao: ProfileDatastoreDao,
            factory: IMqttClientFactory<Mqtt5AsyncClient>
        ): Flow<Product<Mqtt5AsyncClient>> = profileDatastoreDao.getSelectedProfileId()
            .map { it?.let { profileDatabaseDao.getProfile(it) } }.map {
                runProducing {
                    when (it) {
                        null -> throw ProfileNotSelectedException()
                        else -> with(it) {
                            with(it.configuration) {
                                when {
                                    login != null && password != null -> factory.create(
                                        id, host, port, Login(login!!), Password(password!!)
                                    )

                                    configuration.login != null -> factory.create(
                                        id, host, port, Login(login!!)
                                    )

                                    password != null -> factory.create(
                                        id, host, port, Password(password!!)
                                    )

                                    else -> factory.create(id, host, port)
                                }
                            }
                        }
                    }
                }
            }

        fun provideMqttAsyncClientActivatedFlow(
            activator: IMqttClientActivator<Mqtt5AsyncClient>
        ): StateFlow<Product<Mqtt5AsyncClient>> = activator.activatedClient

        fun provideKeyFlow(): Flow<UUID> = flow { emit(UUID.randomUUID()) }
    }
}