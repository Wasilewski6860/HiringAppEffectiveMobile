package ru.hiringapp.feedback

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.feedback.data.FeedbackUiEvent
import ru.hiringapp.feedback.data.FeedbackUiState
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(

) : BaseViewModel<FeedbackUiState, FeedbackUiEvent>() {

    override val _uiState = MutableStateFlow(FeedbackUiState())
    override val uiState: StateFlow<FeedbackUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<FeedbackUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()


}
