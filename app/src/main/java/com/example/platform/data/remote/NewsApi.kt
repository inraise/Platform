package com.example.platform.data.remote

import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "5aa9d300a09149adbddc00c0f0644120"
    ): TopHeadlinesModel
}