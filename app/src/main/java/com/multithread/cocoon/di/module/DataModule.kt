package com.multithread.cocoon.di.module

import android.content.Context
import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.local.AppDataBase
import com.multithread.cocoon.data.local.NewsDao
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.data.network.NewsAPI
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSource
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSourceImpl
import com.multithread.cocoon.di.scope.PerApplication
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.mapper.TopStoryDomainMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    companion object {

        @Provides
        fun provideNewsDao(appDataBase: AppDataBase): NewsDao =
                appDataBase.provideNewsDao()

        @Provides
        @PerApplication
        fun provideDataBase(context: Context): AppDataBase = AppDataBase.create(context)

        @Provides
        fun provideTopStoryRemoteDataSource(
                newsAPI: NewsAPI,
                topStoriesMapper: Mapper<TopStoryDTO, TopStoryDomainEntity>
        ): GetTopStoriesRemoteDataSource =
                GetTopStoriesRemoteDataSourceImpl(newsAPI, topStoriesMapper)
    }


    @Binds
    fun provideTopStoryMapper(mapper: TopStoryDomainMapper): Mapper<TopStoryDTO, TopStoryDomainEntity>

}