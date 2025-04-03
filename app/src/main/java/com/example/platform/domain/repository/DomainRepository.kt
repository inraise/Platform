package com.example.platform.domain.repository

import com.example.platform.domain.models.top_headlines.TopHeadlinesModel

interface DomainRepository {
    suspend fun getTopHeadlines(): TopHeadlinesModel
}