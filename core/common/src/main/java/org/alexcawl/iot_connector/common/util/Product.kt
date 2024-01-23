package org.alexcawl.iot_connector.common.util

import org.alexcawl.iot_connector.common.util.Product.Companion.ofFailure
import org.alexcawl.iot_connector.common.util.Product.Companion.ofSuccess

sealed interface Product<out T> {
    data object Empty : Product<Nothing>

    data class Success<T> internal constructor(val value: T) : Product<T>

    data class Failure<T> internal constructor(val throwable: Throwable) : Product<T>

    companion object {
        @JvmStatic
        fun <T> ofSuccess(value: T) = Success(value)

        @JvmStatic
        fun <T> ofFailure(throwable: Throwable) = Failure<T>(throwable)
    }

}

inline fun <R> runProducing(block: () -> R): Product<R> = try {
    ofSuccess(block())
} catch (exception: Exception) {
    ofFailure(exception)
}