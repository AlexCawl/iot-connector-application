package org.alexcawl.iot_connector.viewer.domain.mapper

import javax.inject.Inject
import kotlin.jvm.Throws

class ThermalMatrixMapper @Inject constructor() : IThermalMatrixMapper {
    @Throws(IllegalArgumentException::class)
    override fun map(from: List<Int>): Result<Array<Array<Float>>> = try {
        Result.success(mapSmall(from))
    } catch (exception: IllegalArgumentException) {
        runCatching { mapLarge(from) }
    }

    private companion object {
        const val LARGE_MATRIX_WIDTH: Int = 24
        const val LARGE_MATRIX_HEIGHT: Int = 32

        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun mapLarge(array: List<Int>): Array<Array<Float>> {
            require(array.size == LARGE_MATRIX_HEIGHT * LARGE_MATRIX_WIDTH)
            return Array(LARGE_MATRIX_HEIGHT) {
                Array(LARGE_MATRIX_WIDTH) { _ ->
                    0f
                }
            }.also {
                fillMatrix(it, array)
            }
        }

        const val SMALL_MATRIX_WIDTH: Int = 8
        const val SMALL_MATRIX_HEIGHT: Int = 8

        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun mapSmall(array: List<Int>): Array<Array<Float>> {
            require(array.size == SMALL_MATRIX_HEIGHT * SMALL_MATRIX_WIDTH)
            return Array(SMALL_MATRIX_HEIGHT) {
                Array(SMALL_MATRIX_WIDTH) { _ ->
                    0f
                }
            }.also {
                fillMatrix(it, array)
            }
        }

        @JvmStatic
        private fun fillMatrix(matrix: Array<Array<Float>>, data: List<Int>) {
            var counter = 0
            matrix.forEachIndexed { i, row ->
                row.forEachIndexed { j, _ ->
                    matrix[i][j] = data[counter++].toFloat()
                }
            }
        }
    }
}