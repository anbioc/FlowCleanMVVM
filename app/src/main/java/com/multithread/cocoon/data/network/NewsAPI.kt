package com.multithread.cocoon.data.network

import com.multithread.cocoon.BuildConfig
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsAPI {
    @GET("{topic}.json")
    suspend fun getTopNews(
        @Path("topic") topic: String = "technology",
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): TopStoryDTO
}