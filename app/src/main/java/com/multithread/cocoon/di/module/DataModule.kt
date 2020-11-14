package com.multithread.cocoon.di.module

import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.data.network.NewsAPI
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSource
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSourceImpl
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Provides
    fun provideTopStoryRemoteDataSource(
        newsAPI: NewsAPI,
        topStoriesMapper: Mapper<TopStoryDTO, TopStoryDomainEntity>
    ): GetTopStoriesRemoteDataSource =
        GetTopStoriesRemoteDataSourceImpl(newsAPI, topStoriesMapper)


}