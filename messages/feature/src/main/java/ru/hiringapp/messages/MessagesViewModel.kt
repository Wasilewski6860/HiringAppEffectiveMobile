package ru.hiringapp.messages

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.messages.data.MessagesUiEvent
import ru.hiringapp.messages.data.MessagesUiState
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(

) : BaseViewModel<MessagesUiState, MessagesUiEvent>() {

    override val _uiState = MutableStateFlow(MessagesUiState())
    override val uiState: StateFlow<MessagesUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<MessagesUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()


}
