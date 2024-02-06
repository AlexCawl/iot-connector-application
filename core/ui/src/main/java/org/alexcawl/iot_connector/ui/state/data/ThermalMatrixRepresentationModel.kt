package org.alexcawl.iot_connector.ui.state.data

data class ThermalMatrixRepresentationModel(
    val matrix: Array<Array<Float>>
) : ViewerDataRepresentationModel {
    override val priority: Int
        get() = 2

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThermalMatrixRepresentationModel

        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }
}