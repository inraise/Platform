package com.example.platform.data.remote

import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=bitcoin&apiKey=5aa9d300a09149adbddc00c0f0644120

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "5aa9d300a09149adbddc00c0f0644120",
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = 80
    ): TopHeadlinesModel

    @GET("everything")
    suspend fun getSearchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = "5aa9d300a09149adbddc00c0f0644120",
        @Query("pageSize") pageSize: Int = 100
    ): TopHeadlinesModel
}