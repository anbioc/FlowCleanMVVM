package com.multithread.cocoon.domain.repository

import com.multithread.cocoon.base.RepositoryStrategy
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.TopStoriesParam
import com.multithread.cocoon.base.repository.StrategyFlowRepository
import com.multithread.cocoon.data.local.GetTopStoriesLocalDataSource
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSource
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.error.ErrorContainer
import javax.inject.Inject

class TopStoriesRepository @Inject constructor(
        private val localTopStoriesDataSource: GetTopStoriesLocalDataSource,
        private val remoteTopStoriesDataSource: GetTopStoriesRemoteDataSource,
        errorContainer: ErrorContainer
) : StrategyFlowRepository<TopStoryDomainEntity, TopStoriesParam>(errorContainer) {

    override suspend fun getLocal(param: TopStoriesParam): ResultResponse<TopStoryDomainEntity> =
        when (param){
            is TopStoriesParam.GetTopStories -> localTopStoriesDataSource.getTopStories()
            is TopStoriesParam.AddToFavorites -> localTopStoriesDataSource.addToFavorites(param.data)
            is TopStoriesParam.RemoveFromFavorites -> localTopStoriesDataSource.removeFromFavorites(param.data)
        }


    override suspend fun getRemote(param: TopStoriesParam): ResultResponse<TopStoryDomainEntity> =
            remoteTopStoriesDataSource.getTopStories()

    override suspend fun saveRemote(data: TopStoryDomainEntity) =
            localTopStoriesDataSource.saveTopStories(data)

}