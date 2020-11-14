package com.multithread.cocoon.domain.usecase

import com.multithread.cocoon.base.AnyParam
import com.multithread.cocoon.base.RepositoryStrategy
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.domain.GeneralUseCase
import com.multithread.cocoon.base.repository.StrategyFlowRepository
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.error.ErrorContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: StrategyFlowRepository<TopStoryDomainEntity, AnyParam>,
    errorContainer: ErrorContainer
) : GeneralUseCase<AnyParam, TopStoryDomainEntity>(errorContainer) {
    @ExperimentalCoroutinesApi
    override suspend fun buildFlow(
        param: AnyParam,
        strategy: RepositoryStrategy
    ): Flow<ResultResponse<TopStoryDomainEntity>> =
        topStoriesRepository.getResult(param, RepositoryStrategy.Remote)

}