package org.alexcawl.iot_connector.common.mappers

import org.alexcawl.iot_connector.common.model.ProfileModel
import org.alexcawl.iot_connector.common.util.TwoWayMapper

interface IProfileMapper <T> : TwoWayMapper<T, ProfileModel>