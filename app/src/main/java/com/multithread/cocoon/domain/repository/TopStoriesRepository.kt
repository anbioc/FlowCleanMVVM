package com.multithread.cocoon.domain.repository

import com.multithread.cocoon.base.AnyParam
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.repository.StrategyFlowRepository
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSource
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.error.ErrorContainer
import javax.inject.Inject

class TopStoriesRepository @Inject constructor(
    private val remoteTopStoriesDataSource: GetTopStoriesRemoteDataSource,
    errorContainer: ErrorContainer
) : StrategyFlowRepository<TopStoryDomainEntity, AnyParam>(errorContainer) {

    override suspend fun getLocal(param: AnyParam): ResultResponse<TopStoryDomainEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getRemote(param: AnyParam): ResultResponse<TopStoryDomainEntity> =
        remoteTopStoriesDataSource.getTopStories()

    override suspend fun saveRemote(data: TopStoryDomainEntity) {
        TODO("Not yet implemented")
    }
}