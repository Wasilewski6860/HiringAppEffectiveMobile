package ru.hiringapp.base_feature.mvvm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

interface NonInjectBaseView<
        UiState : State,
        Event : UiEvent,
        ViewModel : BaseViewModel<UiState, Event>> :
    LifecycleOwner {
    val viewModel: ViewModel

    /**
     * Базовая реализация подписки на изменения состояния UI
     * */
    fun setUiStateFlowCollect() {
        lifecycleScope.launch {
            viewModel
                .uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    handleUiState(it)
                }
        }
    }

    /**
     * Базовая реализация подписки на получение UI событий
     * */
    fun setUiEventFlowCollect() {
        lifecycleScope.launch {
            viewModel
                .uiEvent
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    handleUiEvent(it)
                }
        }
    }

    /**
     * Базовая реализация подписки на получение UI оповещений
     * */
    fun setUiMessageEventFlowCollect() {
        lifecycleScope.launch {
            viewModel
                .uiEvent
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    handleUiMessageEvent(it)
                }
        }
    }

    fun handleUiState(uiState: UiState) {}

    fun handleUiEvent(uiEvent: Event) {}

    fun handleUiMessageEvent(messageEvent: Event) {}
}