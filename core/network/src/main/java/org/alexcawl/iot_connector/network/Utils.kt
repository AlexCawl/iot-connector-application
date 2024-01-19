package org.alexcawl.iot_connector.network

import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5Subscribe

fun Mqtt5AsyncClient.subscribe(endpoint: String, callback: (Mqtt5Publish) -> Unit) = this.subscribe(
    Mqtt5Subscribe.builder()
        .topicFilter(endpoint)
        .qos(MqttQos.AT_LEAST_ONCE)
        .build(),
    callback
)