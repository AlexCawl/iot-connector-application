package org.alexcawl.iot_connector.profile.util

import org.alexcawl.iot_connector.common.model.MQTTConfiguration
import org.alexcawl.iot_connector.persistence.db.entities.MQTTConfigurationEntity
import javax.inject.Inject

class MQTTConfigurationMapper @Inject constructor() : IMQTTConfigurationMapper {
    override fun mapFirst(from: MQTTConfigurationEntity): MQTTConfiguration = with(from) {
        MQTTConfiguration(host, port, login, password)
    }

    override fun mapSecond(from: MQTTConfiguration): MQTTConfigurationEntity = with(from) {
        MQTTConfigurationEntity(host, port, login, password)
    }
}