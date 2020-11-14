package com.multithread.cocoon.base

import com.core.core.error.ErrorEntity
import com.multithread.cocoon.AppConstants
import java.lang.IllegalArgumentException

sealed class ResultResponse<T> {
    data class Success<T>(val data: T) : ResultResponse<T>()
    class Failure<T>(val error: ErrorEntity) : ResultResponse<T>()
    class Loading<T> : ResultResponse<T>()


    fun isSuccess(): Boolean = this is Success<*>
    fun isFailure(): Boolean = this is Failure
    fun isLoading(): Boolean = this is Loading
    fun isEmpty(): Boolean {
        return if (isSuccess()) {
            (this as Success<List<*>>).data.isEmpty()
        } else {
            true
        }
    }

    fun extractData(): T = with(this) {
        if (!isSuccess())
            throw IllegalArgumentException(AppConstants.DATA_NOT_FOUND)

        (this as Success<T>).data
    }

    fun extractError() = with(this){
        if (!isFailure())
            ErrorEntity.NoError
        else {
            (this as Failure<T>).error
        }
    }

}

