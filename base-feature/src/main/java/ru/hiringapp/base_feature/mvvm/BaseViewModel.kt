package ru.hiringapp.base_feature.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<ScreenState : State, ScreenEvent : UiEvent> : ViewModel() {
    protected abstract val _uiState: MutableStateFlow<ScreenState>
    abstract val uiState: StateFlow<ScreenState>

    protected abstract val _uiEvent: MutableSharedFlow<ScreenEvent>
    abstract val uiEvent: SharedFlow<ScreenEvent>
}
