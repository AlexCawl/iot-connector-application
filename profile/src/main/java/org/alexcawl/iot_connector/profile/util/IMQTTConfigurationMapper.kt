package org.alexcawl.iot_connector.profile.util

import org.alexcawl.iot_connector.common.model.MQTTConfiguration
import org.alexcawl.iot_connector.common.util.TwoWayMapper
import org.alexcawl.iot_connector.persistence.db.entities.MQTTConfigurationEntity

interface IMQTTConfigurationMapper : TwoWayMapper<MQTTConfigurationEntity, MQTTConfiguration>