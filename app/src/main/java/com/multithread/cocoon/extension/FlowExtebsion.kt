package com.multithread.cocoon.extension

import com.multithread.cocoon.base.ResultResponse
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.doOnError(onError: (Throwable) -> Unit): Flow<T> {
    return flow {
        try {
            collect { value -> emit(value) }
        } catch (e: Exception) {
            onError(e)
            throw e
        }
    }
}


fun <T> Flow<ResultResponse<T>>.handleSuccessFailure(
    doOnError: (e: Throwable) -> Unit,
    onErrorEmit: (e: Throwable) -> ResultResponse<T>
) = this.catch { e ->
    doOnError(e)
    emit(onErrorEmit(e))
}

fun <T> Flow<T>.handleFailure(
    doOnError: (e: Throwable) -> Unit,
    onErrorEmit: (e: Throwable) -> T
) = this.catch { e ->
    doOnError(e)
    emit(onErrorEmit(e))
}