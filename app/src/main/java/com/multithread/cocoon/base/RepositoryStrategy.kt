package com.multithread.cocoon.base

sealed class RepositoryStrategy {
    object OfflineFirst : RepositoryStrategy()
    object Remote : RepositoryStrategy()
    object Local: RepositoryStrategy()
    object NoStrategy: RepositoryStrategy()
}