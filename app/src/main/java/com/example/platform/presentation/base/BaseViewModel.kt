package com.example.platform.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<S: UIState, in E: UIEvent> : ViewModel() {
    abstract val state: Flow<S>
}