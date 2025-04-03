package com.example.platform.presentation.screens.main

import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.domain.usecase.LoadTopHeadlinesUseCase
import com.example.platform.presentation.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainScreenReducer(
    initial: MainScreenState,
    private val useCase: LoadTopHeadlinesUseCase,
    private val viewModelScope: CoroutineScope
) : Reducer<MainScreenState, MainScreenEvent>(initial) {
    override fun reduce(oldState: MainScreenState, event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.ShowData -> {
                setState(oldState.copy(isLoading = false, data = event.data))
            }

            is MainScreenEvent.LoadingData -> {
                viewModelScope.launch {
                    setState(oldState.copy(isLoading = true, data = TopHeadlinesModel()))
                    try {
                        useCase.invoke().let { data ->
                            if (data.status != "error") {
                                sendEvent(MainScreenEvent.ShowData(data = data))
                            } else {
                                sendEvent(MainScreenEvent.ShowError(errorMessage = "Error"))
                            }

                        }
                    } catch (e: Exception) {
                        sendEvent(
                            MainScreenEvent.ShowError(
                                errorMessage = e.message ?: "Exception"
                            )
                        )
                    }
                }
            }

            is MainScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        data = TopHeadlinesModel(),
                        error = event.errorMessage
                    )
                )
            }
        }
    }
}