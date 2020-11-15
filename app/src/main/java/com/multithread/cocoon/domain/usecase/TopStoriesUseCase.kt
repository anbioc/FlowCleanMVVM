package com.multithread.cocoon.domain.usecase

import com.multithread.cocoon.base.RepositoryStrategy
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.TopStoriesParam
import com.multithread.cocoon.base.domain.GeneralUseCase
import com.multithread.cocoon.base.repository.StrategyFlowRepository
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.error.ErrorContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: StrategyFlowRepository<TopStoryDomainEntity, TopStoriesParam>,
    errorContainer: ErrorContainer
) : GeneralUseCase<TopStoriesParam, TopStoryDomainEntity>(errorContainer) {
    @ExperimentalCoroutinesApi
    override suspend fun buildFlow(
        param: TopStoriesParam,
        strategy: RepositoryStrategy
    ): Flow<ResultResponse<TopStoryDomainEntity>> = when (param) {
        is TopStoriesParam.AddToFavorites -> topStoriesRepository.getResult(
            param,
            RepositoryStrategy.Local
        )
        is TopStoriesParam.RemoveFromFavorites -> topStoriesRepository.getResult(
            param,
            RepositoryStrategy.Local
        )
        is TopStoriesParam.GetTopStories -> topStoriesRepository.getResult(
            param,
            RepositoryStrategy.OfflineFirst
        )

    }


}