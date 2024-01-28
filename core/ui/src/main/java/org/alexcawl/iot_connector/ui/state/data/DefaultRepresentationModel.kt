package org.alexcawl.iot_connector.ui.state.data

import androidx.compose.runtime.Immutable

@Immutable
data class DefaultRepresentationModel(val bytes: ByteArray): ViewerDataRepresentationModel {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DefaultRepresentationModel

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}