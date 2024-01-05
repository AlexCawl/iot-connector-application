package org.alexcawl.iot_connector.profile.data

import org.alexcawl.iot_connector.common.model.Profile
import org.alexcawl.iot_connector.common.util.TwoWayMapper
import org.alexcawl.iot_connector.persistence.db.entities.ProfileEntity

interface IProfileMapper : TwoWayMapper<ProfileEntity, Profile>