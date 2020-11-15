package com.multithread.cocoon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: TopStoryLocalEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdateAll(item: List<TopStoryLocalEntity>)

    @Query("SELECT * FROM story")
    suspend fun getTopStories(): List<TopStoryLocalEntity>
}