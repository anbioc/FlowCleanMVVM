package com.multithread.cocoon.base

import com.multithread.cocoon.error.ErrorEntity

data class BaseState(
    var error: ErrorEntity = ErrorEntity.NoError,
    var loading: Boolean = true,
) {

    fun noError() = this.copy(
        error = ErrorEntity.NoError,
    )

    fun noErrorNoLoading() = this.copy(
        error = ErrorEntity.NoError,
        loading = false,
    )

    fun onErrorNoLoading(failure: ErrorEntity) = this.copy(
        error  = ErrorEntity.NotFound(""),
        loading = false,
    )

    fun loading() = this.copy(
        error = ErrorEntity.NoError,
        loading = true,
    )

}



abstract class ViewModelState {
    open var baseState: BaseState = BaseState()
}

fun <T : ViewModelState> T.loading(): T = with(this) {
    this.baseState.loading = true
    this
}

interface BaseEvent


