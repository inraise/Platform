package com.example.platform.domain.usecase

import com.example.platform.data.repository.DomainRepositoryImpl
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import javax.inject.Inject

class LoadTopHeadlinesUseCase @Inject constructor(
    private val domainRepository: DomainRepositoryImpl
) : BaseUseCase<TopHeadlinesModel>() {
    override suspend fun invoke(): TopHeadlinesModel = domainRepository.getTopHeadlines()
}