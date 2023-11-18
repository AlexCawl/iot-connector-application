package org.alexcawl.iot_connector.common.util

interface TwoWayMapper <A, B> {
    fun mapFirst(from: A): B

    fun mapSecond(from: B): A
}