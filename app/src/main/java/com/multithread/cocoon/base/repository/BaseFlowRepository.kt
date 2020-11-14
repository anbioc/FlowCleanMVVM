package com.multithread.cocoon.base.repository

import com.multithread.cocoon.base.Param
import com.multithread.cocoon.base.RepositoryStrategy
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.error.ErrorContainer
import com.multithread.cocoon.extension.handleSuccessFailure
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*


abstract class StrategyFlowRepository<T, PARAM : Param>(
    private val errorContainer: ErrorContainer
) : BaseFlowRepository<T, PARAM> {

    @ExperimentalCoroutinesApi
    override suspend fun getResult(
        param: PARAM, strategy: RepositoryStrategy
    ): Flow<ResultResponse<T>> = when (strategy) {
        is RepositoryStrategy.Remote -> getRemoteEmission(param)
        is RepositoryStrategy.OfflineFirst -> getOfflineFirst(param)
        is RepositoryStrategy.Local -> getLocalEmission(param)
        else -> getOfflineFirst(param)
    }


    /**
     * Provides data based on OfflineFirst strategy, first emits local data,
     * then goes for remote data and if it's available it will save data into db
     * and again delivers the local data, respecting SingleSourceOfTruth.
     */
    @ExperimentalCoroutinesApi
    private suspend fun getOfflineFirst(param: PARAM) = flow {
        emit(getLocal(param))
        getRemote(param).apply {
            if (this.isSuccess()) {
                saveRemote((this as ResultResponse.Success<T>).data)
                emit(getLocal(param))
            } else {
                emit(this)
            }
            
        }
    }.handleSuccessFailure(
        doOnError = {
            it.printStackTrace()
        },
        onErrorEmit = { ResultResponse.Failure(errorContainer.getError(it)) })

    /**
     * Gets local data, handles [Exception] in between.
     *@return [ResultResponse.Failure] if any [Exception] happens,
     * otherwise it's a type of [ResultResponse.Success].
     */
    private suspend fun getLocalEmission(param: PARAM) = flow {
        emit(getLocal(param))
    }.handleSuccessFailure(
        doOnError = {
            // TODO: Implement Timber for debugging purposes.
            it.printStackTrace()
        },
        onErrorEmit = { ResultResponse.Failure(errorContainer.getError(it)) })

    /**
     * Gets remote data, handles [Exception] in between.
     *@return [ResultResponse.Failure] if any [Exception] happens,
     * otherwise it's a type of [ResultResponse.Success].
     */
    private suspend fun getRemoteEmission(param: PARAM): Flow<ResultResponse<T>> = flow {
        emit(getRemote(param))
    }.handleSuccessFailure(
        doOnError = {
            // TODO: Implement Timber for debugging purposes.
            it.printStackTrace()
        },
        onErrorEmit = { ResultResponse.Failure(errorContainer.getError(it)) })


    /**
     * Gets local data from provided data source. override it to implement your entity logic.
     */
    abstract suspend fun getLocal(param: PARAM): ResultResponse<T>

    /**
     * Gets remote data from provided data source. override it to implement your entity logic.
     */
    abstract suspend fun getRemote(param: PARAM): ResultResponse<T>

    /**
     * Saves remote data into local data base.
     */
    abstract suspend fun saveRemote(data: T)

}


/**
 * Base repository interface for operations that's going to use [Flow].
 */
interface BaseFlowRepository<T, PARAM : Param> {

    /**
     * The main method that's going to be called from clients.
     * @param param contains necessary parameters for the operation.
     * @param strategy decides how data should be fetched.
     */
    suspend fun getResult(param: PARAM, strategy: RepositoryStrategy): Flow<ResultResponse<T>>

}

