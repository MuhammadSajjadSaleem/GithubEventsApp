package com.sajjad.core.common

suspend fun <T> safeCall(block: suspend () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (t: Throwable) {
        Result.Error(t)
    }
}
