package com.multithread.cocoon.error

interface ErrorContainer {
    fun getError(throwable: Throwable): ErrorEntity
}
