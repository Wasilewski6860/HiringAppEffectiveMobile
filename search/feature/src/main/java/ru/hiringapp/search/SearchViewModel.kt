package ru.hiringapp.search

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.search.data.SearchUiEvent
import ru.hiringapp.search.data.SearchUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : BaseViewModel<SearchUiState, SearchUiEvent>() {

    override val _uiState = MutableStateFlow(SearchUiState())
    override val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<SearchUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()


}
