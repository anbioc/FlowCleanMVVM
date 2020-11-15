package com.multithread.cocoon.data.local

import com.multithread.cocoon.base.BaseDataSource
import com.multithread.cocoon.base.LocalMapper
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.wrapAroundSuccessResponse
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

interface GetTopStoriesLocalDataSource : BaseDataSource {
    suspend fun saveTopStories(items: TopStoryDomainEntity)
    suspend fun getTopStories(): ResultResponse<TopStoryDomainEntity>
}

class GetTopStoriesLocalDataSourceImpl @Inject constructor(
        private val newsDao: NewsDao,
        private val mapper: LocalMapper<List<TopStoryLocalEntity>, TopStoryDomainEntity>
) : GetTopStoriesLocalDataSource {

    override suspend fun saveTopStories(items: TopStoryDomainEntity) =
            newsDao.insertOrUpdateAll(mapper.mapToLocal(items))

    override suspend fun getTopStories(): ResultResponse<TopStoryDomainEntity> =
            mapper.mapFromLocal(newsDao.getTopStories()).wrapAroundSuccessResponse()

}