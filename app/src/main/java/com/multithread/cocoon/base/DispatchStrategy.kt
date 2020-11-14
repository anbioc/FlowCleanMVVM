package com.multithread.cocoon.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Determines the Coroutine [CoroutineDispatcher] Strategy.
 */
sealed class DispatchStrategy {

    object IO : DispatchStrategy()
    object MAIN : DispatchStrategy()
    object Default : DispatchStrategy()
    object Unconfined : DispatchStrategy()

    private fun isIO() = (this == IO)
    private fun isMAIN() = (this == MAIN)
    private fun isDefault() = (this == Default)
    private fun isUnconfined() = (this == Unconfined)

    fun decideDispatcher(): CoroutineDispatcher =
        when {
            this.isIO() -> Dispatchers.IO
            this.isDefault() -> Dispatchers.Default
            this.isMAIN() -> Dispatchers.Main
            this.isUnconfined() -> Dispatchers.Unconfined
            else -> Dispatchers.IO
        }


}