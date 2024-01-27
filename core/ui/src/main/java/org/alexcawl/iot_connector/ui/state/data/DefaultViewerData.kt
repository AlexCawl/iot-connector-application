package org.alexcawl.iot_connector.ui.state.data

import androidx.compose.runtime.Immutable

@Immutable
data class DefaultViewerData(val bytes: ByteArray): ViewerData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultViewerData

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}