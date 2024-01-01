package org.alexcawl.iot_connector.common.model

import org.alexcawl.iot_connector.common.util.Builder
import java.util.UUID
import kotlin.jvm.Throws

class ProfileBuilder(
    val name: String?,
    val info: String?,
    val host: String?,
    val port: String?,
    val login: String?,
    val password: String?
) : Builder<Profile> {
    @Throws(ProfileBuildException::class)
    override fun build(): Profile {
        val validName: String = name ?: throw ProfileBuildException.ProfileNameException
        val validInfo: String? = info

        val validHost: String = host ?: throw ProfileBuildException.ConfigurationHostIsEmpty
        val validPort: Int = port?.let {
            return@let try {
                it.toInt()
            } catch (exception: NumberFormatException) {
                throw ProfileBuildException.ConfigurationPortIsNotNumber
            }
        } ?: throw ProfileBuildException.ConfigurationPortIsEmpty
        val validLogin: String? = login
        val validPassword: String? = password

        return Profile(
            id = UUID.randomUUID(),
            name = validName,
            createdAt = System.currentTimeMillis(),
            host = validHost,
            port = validPort,
            login = validLogin,
            password = validPassword,
            info = validInfo,
            changedAt = null
        )
    }
}