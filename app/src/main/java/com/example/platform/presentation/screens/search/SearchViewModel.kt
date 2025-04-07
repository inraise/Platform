package com.example.platform.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platform.domain.models.top_headlines.TopHeadlinesModel
import com.example.platform.domain.usecase.LoadSearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val loadSearchNewsUseCase: LoadSearchNewsUseCase) :
    ViewModel() {
    private var searchData by mutableStateOf(TopHeadlinesModel())

    val searchDataValue
        get() = searchData

    fun getSearchNews(q: String) {
        viewModelScope.launch {
            searchData = loadSearchNewsUseCase.invoke(textData = q).copy()
        }
    }
}