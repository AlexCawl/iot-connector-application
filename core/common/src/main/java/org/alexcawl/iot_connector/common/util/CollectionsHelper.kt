package org.alexcawl.iot_connector.common.util

fun Array<Float>.averageOrNull(): Float? = when {
    this.isEmpty() -> null
    else -> this.sum() / this.size
}

fun Array<Float>.medianOrNull(): Float? = when {
    this.isEmpty() -> null
    else -> this[this.size / 2]
}