package com.multithread.cocoon.base

import com.core.core.base.ActivityId
import com.core.core.base.DirectionId
import com.core.core.base.NavigationHolder
import com.multithread.cocoon.error.ErrorEntity

data class BaseState(
    var error: ErrorEntity = ErrorEntity.NoError,
    var loading: Boolean = true,
    var navigation: NavigationHolder = NavigationHolder.NoNav
) {

    fun noError() = this.copy(
        error = ErrorEntity.NoError,
        navigation = NavigationHolder.NoNav
    )

    fun noErrorNoLoading() = this.copy(
        error = ErrorEntity.NoError,
        loading = false,
        navigation = NavigationHolder.NoNav
    )

    fun onErrorNoLoading(failure: ErrorEntity) = this.copy(
        error = failure,
        loading = false,
        navigation = NavigationHolder.NoNav
    )

    fun loading() = this.copy(
        error = ErrorEntity.NoError,
        loading = true,
        navigation = NavigationHolder.NoNav
    )

    fun navigateTo(id: String = "", directionId: DirectionId) = this.copy(
        error = ErrorEntity.NoError,
        loading = false,
        navigation = if (id.isEmpty().not()) {
            NavigationHolder.ToDirection(id, directionId)
        } else {
            NavigationHolder.ToDirection(navDirectionId = directionId)
        }
    )

    fun navigateTo(id: String = "", directionId: ActivityId) = this.copy(
        error = ErrorEntity.NoError,
        loading = false,
        navigation = if (id.isEmpty().not()) {
            NavigationHolder.ToActivity(id, directionId)
        } else {
            NavigationHolder.ToActivity(navDirectionId = directionId)
        }
    )

}


abstract class ViewModelState {
    open var baseState: BaseState = BaseState()
}

fun <T : ViewModelState> T.loading(): T = with(this) {
    this.baseState.loading = true
    this
}

