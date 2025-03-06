package ru.hiringapp.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.base_feature.second_navigation.NavigationManager
import ru.hiringapp.search.blocks.BlockDataUi
import ru.hiringapp.search.blocks.BlocksMapper
import ru.hiringapp.search.data.SearchUiEvent
import ru.hiringapp.search.data.SearchUiState
import ru.hiringapp.search.interactor.api.usecase.GetOffersUseCase
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val blocksMapper: BlocksMapper,
    private val navigationManager: NavigationManager,
) : BaseViewModel<SearchUiState, SearchUiEvent>() {

    override val _uiState = MutableStateFlow(SearchUiState())
    override val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<SearchUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                val offers = withContext(Dispatchers.IO) {
                    getOffersUseCase()
                }
                val mappedItems = blocksMapper(offers) + blocksMapper(offers)
                _uiState.update {
                    it.copy(items = mappedItems)
                }
            } catch (e: Exception) {
                // Обработка ошибки
//                Log.e("ViewModel", "Ошибка при загрузке offers", e)
                Log.e("Ошибка при загрузке offers", e.message?:" Теста ошибки нет")

            }
        }
    }

    fun onOfferClick(offer: BlockDataUi.OfferItem) {

    }
}
