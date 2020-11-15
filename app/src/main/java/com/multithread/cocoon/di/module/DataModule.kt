package com.multithread.cocoon.di.module

import android.content.Context
import com.multithread.cocoon.base.LocalMapper
import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.local.AppDataBase
import com.multithread.cocoon.data.local.GetTopStoriesLocalDataSource
import com.multithread.cocoon.data.local.GetTopStoriesLocalDataSourceImpl
import com.multithread.cocoon.data.local.NewsDao
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity
import com.multithread.cocoon.data.network.NewsAPI
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSource
import com.multithread.cocoon.data.remote.GetTopStoriesRemoteDataSourceImpl
import com.multithread.cocoon.di.scope.PerApplication
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.mapper.TopStoryDomainMapper
import com.multithread.cocoon.mapper.TopStoryLocalMapper
import com.multithread.cocoon.mapper.TopStoryLocalSingleMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    companion object {

        @Provides
        fun provideTopStoryLocalMapper(): LocalMapper<List<TopStoryLocalEntity>, TopStoryDomainEntity> =
            TopStoryLocalMapper()

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

        @Provides
        fun provideTopStoriesLocalDataBase(
            newsDao: NewsDao,
            mapper: LocalMapper<List<TopStoryLocalEntity>, TopStoryDomainEntity>,
            singleMapper: Mapper<TopStoryDomainEntity.Result, TopStoryLocalEntity>
        ): GetTopStoriesLocalDataSource =
            GetTopStoriesLocalDataSourceImpl(newsDao, mapper, singleMapper)
    }

    @Binds
    fun provideTopStoryMapper(mapper: TopStoryDomainMapper): Mapper<TopStoryDTO, TopStoryDomainEntity>

    @Binds
    fun provideSingleLocalTopStoryMapper(mapper: TopStoryLocalSingleMapper): Mapper<TopStoryDomainEntity.Result, TopStoryLocalEntity>
}