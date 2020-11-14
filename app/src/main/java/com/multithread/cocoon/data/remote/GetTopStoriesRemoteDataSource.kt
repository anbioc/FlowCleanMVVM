package com.multithread.cocoon.data.remote

import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.data.network.NewsAPI
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

interface GetTopStoriesRemoteDataSource {
    suspend fun getTopStories(): TopStoryDomainEntity
}

class GetTopStoriesRemoteDataSourceImpl @Inject constructor(
    private val newsAPI: NewsAPI,
    private val topStoriesMapper: Mapper<TopStoryDTO, TopStoryDomainEntity>
) : GetTopStoriesRemoteDataSource {
    override suspend fun getTopStories(): TopStoryDomainEntity =
        topStoriesMapper.map(newsAPI.getTopNews())


}