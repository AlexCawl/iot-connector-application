package org.alexcawl.iot_connector.common.model

data class Connection(
    val name: String,
    val endpoint: String,
    val icon: Icon = Icon.DEFAULT
) {
    enum class Icon {
        THERMAL,
        DEFAULT
    }
}