package com.multithread.cocoon.data.network

import com.multithread.cocoon.data.model.dto.TopStoryDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsAPI {
    @GET("{topic}.json")
    suspend fun getTopNews(
        @Path("topic") topic: String = "technology"
    ): TopStoryDTO
}