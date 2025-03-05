package ru.hiringapp.favourites

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.favourites.data.FavouritesUiEvent
import ru.hiringapp.favourites.data.FavouritesUiState
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(

) : BaseViewModel<FavouritesUiState, FavouritesUiEvent>() {

    override val _uiState = MutableStateFlow(FavouritesUiState())
    override val uiState: StateFlow<FavouritesUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<FavouritesUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()


}
