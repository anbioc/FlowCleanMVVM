package com.multithread.cocoon.di.module

import com.multithread.cocoon.base.TopStoriesParam
import com.multithread.cocoon.base.repository.StrategyFlowRepository
import com.multithread.cocoon.data.local.GetTopStoriesLocalDataSource
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSource
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.domain.repository.TopStoriesRepository
import com.multithread.cocoon.error.ErrorContainer
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideTopStoryRepository(
            localTopStoriesDataSource: GetTopStoriesLocalDataSource,
            remoteTopStoriesDataSource: GetTopStoriesRemoteDataSource,
            errorContainer: ErrorContainer
    ): StrategyFlowRepository<TopStoryDomainEntity, TopStoriesParam> =
            TopStoriesRepository(
                    localTopStoriesDataSource,
                    remoteTopStoriesDataSource,
                    errorContainer
            )
}