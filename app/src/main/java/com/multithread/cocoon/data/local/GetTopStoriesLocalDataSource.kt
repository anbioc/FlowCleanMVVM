package com.multithread.cocoon.data.local

import com.multithread.cocoon.AppConstants
import com.multithread.cocoon.base.*
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.error.ErrorEntity
import okhttp3.internal.wait
import javax.inject.Inject

interface GetTopStoriesLocalDataSource : BaseDataSource {
    suspend fun saveTopStories(items: TopStoryDomainEntity)
    suspend fun getTopStories(): ResultResponse<TopStoryDomainEntity>
    suspend fun addToFavorites(data: TopStoryDomainEntity.Result): ResultResponse<TopStoryDomainEntity>
    suspend fun removeFromFavorites(data: TopStoryDomainEntity.Result): ResultResponse<TopStoryDomainEntity>
    suspend fun getFavoriteTopStories(): ResultResponse<TopStoryDomainEntity>
}

class GetTopStoriesLocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val mapper: LocalMapper<List<TopStoryLocalEntity>, TopStoryDomainEntity>,
    private val singleMapper: Mapper<TopStoryDomainEntity.Result, TopStoryLocalEntity>
) : GetTopStoriesLocalDataSource {

    override suspend fun getFavoriteTopStories(): ResultResponse<TopStoryDomainEntity> =
        mapper.mapFromLocal(
            newsDao.getTopStories().filter {
                it.favorite
            }
        ).wrapAroundSuccessResponse()


    override suspend fun removeFromFavorites(data: TopStoryDomainEntity.Result): ResultResponse<TopStoryDomainEntity> =
        newsDao.insertOrUpdate(
            singleMapper.map(data.copy(favorite = false))
        ).run {
            getTopStories()
        }

    override suspend fun addToFavorites(data: TopStoryDomainEntity.Result): ResultResponse<TopStoryDomainEntity> =
        newsDao.insertOrUpdate(
            singleMapper.map(data.copy(favorite = true))
        ).run {
            getTopStories()
        }

    override suspend fun saveTopStories(items: TopStoryDomainEntity) =
        newsDao.insertOrUpdateAll(mapper.mapToLocal(items))

    override suspend fun getTopStories(): ResultResponse<TopStoryDomainEntity> =
        newsDao.getTopStories().run {
            if (this.isEmpty()) {
                ResultResponse.Failure(ErrorEntity.NotFound(AppConstants.DATA_NOT_FOUND))
            } else {
                mapper.mapFromLocal(newsDao.getTopStories()).wrapAroundSuccessResponse()
            }
        }

}