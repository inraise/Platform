package com.example.platform.data.repository

import com.example.platform.data.remote.NewsApi
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.domain.repository.DomainRepository
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(private val newsApi: NewsApi) :
    DomainRepository {
    override suspend fun getTopHeadlines(): TopHeadlinesModel {
        return newsApi.getTopHeadlines()
    }
}