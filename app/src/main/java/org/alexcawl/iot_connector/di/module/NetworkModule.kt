package org.alexcawl.iot_connector.di.module

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.iot_connector.di.ApplicationScope
import org.alexcawl.iot_connector.network.mqtt.IMqttClientDao
import org.alexcawl.iot_connector.network.mqtt.IMqttClientHolder
import org.alexcawl.iot_connector.network.mqtt.IMqttClientFactory
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientDao
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientHolder
import org.alexcawl.iot_connector.network.mqtt.MqttAsyncClientFactory
import org.alexcawl.iot_connector.network.transcriber.IMqttResponseTranscriber
import org.alexcawl.iot_connector.network.transcriber.ThermalResponseTranscriber
import org.alexcawl.iot_connector.network.transcriber.TextResponseTranscriber
import java.util.UUID

@Module
interface NetworkModule {
    @Binds
    fun bindMqttFactory(asyncFactory: MqttAsyncClientFactory): IMqttClientFactory<Mqtt5AsyncClient>

    @Binds
    fun bindMqttActivator(activator: MqttAsyncClientHolder): IMqttClientHolder<Mqtt5AsyncClient>

    @Binds
    fun bindMqttClientDao(clientDao: MqttAsyncClientDao): IMqttClientDao<Mqtt5Publish>

    @Binds
    @IntoSet
    fun bindTextTranscriber(transcriber: TextResponseTranscriber): IMqttResponseTranscriber<Mqtt5Publish>

    @Binds
    @IntoSet
    fun bindThermalTranscriber(transcriber: ThermalResponseTranscriber): IMqttResponseTranscriber<Mqtt5Publish>

    companion object {
        @Provides
        @ApplicationScope
        fun provideKeyFlow(): Flow<UUID> = flow { emit(UUID.randomUUID()) }
    }
}