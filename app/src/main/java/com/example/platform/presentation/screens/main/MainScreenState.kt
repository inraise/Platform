package com.example.platform.presentation.screens.main

import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.presentation.base.UIState
import javax.annotation.concurrent.Immutable

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: TopHeadlinesModel,
    val error: String? = null
) : UIState {
    companion object {
        fun initial() =
            MainScreenState(isLoading = true, data = TopHeadlinesModel(), error = null)
    }
}