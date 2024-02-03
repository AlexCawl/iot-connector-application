package org.alexcawl.iot_connector.viewer.data

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.common.model.MqttResponse
import org.alexcawl.iot_connector.common.model.MqttResponsePayload
import org.alexcawl.iot_connector.network.mqtt.IMqttClientDao
import org.alexcawl.iot_connector.network.transcriber.IMqttResponseTranscriber
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao
import java.util.UUID
import javax.inject.Inject

class MqttResponseRepository @Inject constructor(
    private val clientDao: IMqttClientDao<Mqtt5Publish>,
    private val connectionDao: ConnectionDatabaseDao,
    private val transcribers: @JvmSuppressWildcards Set<IMqttResponseTranscriber<Mqtt5Publish>>
) : IMqttResponseRepository {
    override fun subscribe(connectionId: UUID): Flow<Result<MqttResponse>> = flow {
        try {
            val connection = connectionDao.getConnection(connectionId) ?: throw IllegalStateException("Connection entity should exist!")
            clientDao.subscribe(connection.endpoint).map { publish ->
                runCatching {
                    val unboxedPublish = publish.getOrThrow()
                    val responses: List<MqttResponsePayload> = transcribers
                        .map {
                            it.transcribe(unboxedPublish)
                        }
                        .mapNotNull {
                            it.getOrNull()
                        }
                    responses
                }
            }.map {
                runCatching {
                    val payloads = it.getOrThrow()
                    MqttResponse(
                        connection.id,
                        connection.endpoint,
                        connection.name,
                        payloads
                    )
                }
            }.collect {
                emit(it)
            }
        } catch (exception: RuntimeException) {
            emit(Result.failure(exception))
        }
    }
}