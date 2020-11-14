package com.multithread.cocoon.base.domain


import com.multithread.cocoon.base.DispatchStrategy
import com.multithread.cocoon.base.Param
import com.multithread.cocoon.base.RepositoryStrategy
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.error.ErrorContainer
import com.multithread.cocoon.extension.handleSuccessFailure
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * Base UseCase for operations that's going to use [Flow].
 */
interface FlowUseCase<PARAMS : Param, RESULT> {

    /**
     * Starts use case operation.
     * @param param is the input for the execute.
     * @param strategy is the [RepositoryStrategy] that determines how data is going to be fetched.
     * @param dispatchStrategy determines the Dispatcher that the operation should use.
     */
    suspend fun execute(
        param: PARAMS,
        strategy: RepositoryStrategy = RepositoryStrategy.Remote,
        dispatchStrategy: DispatchStrategy = DispatchStrategy.IO
    ): Flow<ResultResponse<RESULT>>

}

/**
 * General implementation of the [FlowUseCase], actual use cases should inherit from this class.
 */
abstract class GeneralUseCase<PARAMS : Param, RESULT>(private val errorContainer: ErrorContainer) :
    FlowUseCase<PARAMS, RESULT> {

    @FlowPreview
    override suspend fun execute(
        param: PARAMS,
        strategy: RepositoryStrategy,
        dispatchStrategy: DispatchStrategy
    ): Flow<ResultResponse<RESULT>> =
        buildFlow(param, strategy)
            .handleSuccessFailure(
                doOnError = {
                    it.printStackTrace()
                },
                onErrorEmit = { ResultResponse.Failure(errorContainer.getError(it)) })
            .flowOn(dispatchStrategy.decideDispatcher())


    /**
     * The actual logic of the use case is going to take place here.
     * @param param is the input for the execute method.
     * @param strategy is the [RepositoryStrategy] that determines how data is going to be fetched.
     */
    protected abstract suspend fun buildFlow(
        param: PARAMS,
        strategy: RepositoryStrategy
    ): Flow<ResultResponse<RESULT>>
}


