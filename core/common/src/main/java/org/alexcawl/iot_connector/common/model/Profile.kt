package org.alexcawl.iot_connector.common.model

import java.util.UUID
import kotlin.jvm.Throws
import kotlin.properties.Delegates

data class Profile(
    val id: UUID,
    val name: String,
    val createdAt: Long,
    val configuration: MQTTConfiguration,
    val info: String? = null,
    val changedAt: Long? = null
) {
    class ProfileBuilder {
        private lateinit var name: String
        private lateinit var host: String
        private var port by Delegates.notNull<Int>()

        private var info: String? = null
        private var login: String? = null
        private var password: String? = null

        fun setName(name: String): ProfileBuilder = this.apply {
            this.name = name
        }

        fun setHost(host: String): ProfileBuilder = this.apply {
            this.host = host
        }

        fun setPort(port: Int): ProfileBuilder = this.apply {
            this.port = port
        }

        fun setInfo(info: String): ProfileBuilder = this.apply {
            this.info = info
        }

        fun setLogin(login: String): ProfileBuilder = this.apply {
            this.login = login
        }

        fun setPassword(password: String): ProfileBuilder = this.apply {
            this.password = password
        }

        @Throws(IllegalArgumentException::class)
        fun build(): Profile {
            return try {
                Profile(
                    id = UUID.randomUUID(),
                    name = name,
                    createdAt = System.currentTimeMillis(),
                    configuration = MQTTConfiguration(host, port, login, password),
                    info = info,
                    changedAt = null
                )
            } catch (exception: Exception) {
                throw IllegalArgumentException(exception)
            }
        }
    }
}
