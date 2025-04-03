package com.example.platform.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.example.platform.domain.usecase.LoadTopHeadlinesUseCase
import com.example.platform.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadTopHeadlinesUseCase: LoadTopHeadlinesUseCase
) : BaseViewModel<MainScreenState, MainScreenEvent>() {
    private val reducer = MainScreenReducer(
        initial = MainScreenState.initial(),
        useCase = loadTopHeadlinesUseCase,
        viewModelScope = viewModelScope
    )

    override val state: StateFlow<MainScreenState>
        get() = reducer.state

    init {
        sendEvent(MainScreenEvent.LoadingData)
    }

    fun sendEvent(event: MainScreenEvent) {
        reducer.sendEvent(event)
    }
}