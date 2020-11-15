package com.multithread.cocoon.data.remote

import com.multithread.cocoon.base.BaseDataSource
import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.wrapAroundSuccessResponse
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.data.network.NewsAPI
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

interface GetTopStoriesRemoteDataSource: BaseDataSource {
    suspend fun getTopStories(): ResultResponse<TopStoryDomainEntity>
}

class GetTopStoriesRemoteDataSourceImpl @Inject constructor(
    private val newsAPI: NewsAPI,
    private val topStoriesMapper: Mapper<TopStoryDTO, TopStoryDomainEntity>
) : GetTopStoriesRemoteDataSource {
    override suspend fun getTopStories(): ResultResponse<TopStoryDomainEntity> =
        topStoriesMapper.map(newsAPI.getTopNews()).wrapAroundSuccessResponse()
}