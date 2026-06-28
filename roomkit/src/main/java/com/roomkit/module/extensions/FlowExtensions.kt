package com.roomkit.module.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.onSuccess(action: suspend (T) -> Unit): Flow<T> =
    this.also { flow ->
        flow.catch { }
    }

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> =
    this.map<T, Result<T>> { Result.Success(it) }
        .catch { emit(Result.Error(it)) }