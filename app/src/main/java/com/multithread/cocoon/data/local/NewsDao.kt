package com.multithread.cocoon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: TopStoryLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAll(item: List<TopStoryLocalEntity>)

    suspend fun getTopStories(): List<TopStoryLocalEntity>
}