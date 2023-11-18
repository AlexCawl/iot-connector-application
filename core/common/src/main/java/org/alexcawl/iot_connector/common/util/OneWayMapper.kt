package org.alexcawl.iot_connector.common.util

interface OneWayMapper <A, B> {
    fun map(from: A): B
}