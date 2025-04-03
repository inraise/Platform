package com.example.platform.presentation.screens.main

import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.presentation.base.UIEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class MainScreenEvent : UIEvent {
    object LoadingData : MainScreenEvent()

    data class ShowData(val data: TopHeadlinesModel) : MainScreenEvent()
    data class ShowError(val errorMessage: String) : MainScreenEvent()
}