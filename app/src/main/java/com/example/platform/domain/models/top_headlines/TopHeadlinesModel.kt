package com.example.platform.domain.models.top_headlines

data class TopHeadlinesModel(
    val status: String = "",
    val articles: List<Article> = listOf()
)