package com.multithread.cocoon.base.repository


import com.multithread.cocoon.base.Param
import com.multithread.cocoon.base.RepositoryStrategy
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


interface Repository<T> {

    fun getResult(param: Param, strategy: RepositoryStrategy): Observable<T> =
        when (strategy) {
            is RepositoryStrategy.Remote -> getRemote(param)
            is RepositoryStrategy.OfflineFirst -> getOfflineFirst(param)
            is RepositoryStrategy.Local -> getOffline(param)
            else -> getOfflineFirst(param)
        }.flatMap {
            Observable.just(it)
        }

    private fun getOfflineFirst(param: Param): Observable<T> =
        Observable.concatArrayEagerDelayError(
            getOffline(param),
            getRemote(param).timeout(15, TimeUnit.SECONDS)
        ).firstOrError().toObservable()

    fun getOffline(param: Param): Observable<T>

    fun getRemote(param: Param): Observable<T>
}