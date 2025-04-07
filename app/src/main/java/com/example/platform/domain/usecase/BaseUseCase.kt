package com.example.platform.domain.usecase

abstract class BaseUseCase<T> {
    abstract suspend fun invoke(textData: String): T
}