package org.alexcawl.iot_connector.di.module

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.iot_connector.di.ApplicationScope
import org.alexcawl.iot_connector.network.mqtt.IMqttClientHolder
import org.alexcawl.iot_connector.network.mqtt.IMqttClientFactory
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientHolder
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientFactory
import java.util.UUID

@Module
interface NetworkModule {
    @Binds
    fun bindMqttFactory(asyncFactory: MqttAsyncClientFactory): IMqttClientFactory<Mqtt5AsyncClient>

    @Binds
    fun bindMqttActivator(activator: MqttAsyncClientHolder): IMqttClientHolder<Mqtt5AsyncClient>

    companion object {
        @Provides
        @ApplicationScope
        fun provideKeyFlow(): Flow<UUID> = flow { emit(UUID.randomUUID()) }
    }
}