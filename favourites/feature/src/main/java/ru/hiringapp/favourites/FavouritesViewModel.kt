package ru.hiringapp.favourites

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
import ru.hiringapp.favourites.data.FavouritesUiEvent
import ru.hiringapp.favourites.data.FavouritesUiState
import ru.hiringapp.vacancy.VacancyItem
import ru.hiringapp.vacancy.api.usecase.ChangeVacancyIsFavouriteUseCase
import ru.hiringapp.vacancy.api.usecase.ObserveFavouriteVacanciesUseCase
import javax.inject.Inject

@HiltViewModel
internal class FavouritesViewModel @Inject constructor(
    private val observeFavouriteVacanciesUseCase: ObserveFavouriteVacanciesUseCase,
    private val vacanciesMapper: FavouriteVacanciesMapper,
    private val changeVacancyIsFavouriteUseCase: ChangeVacancyIsFavouriteUseCase,
) : BaseViewModel<FavouritesUiState, FavouritesUiEvent>() {

    override val _uiState = MutableStateFlow(FavouritesUiState())
    override val uiState: StateFlow<FavouritesUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<FavouritesUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        subscribeOnVacancies()
    }

    private fun subscribeOnVacancies() {
        viewModelScope.launch {
            observeFavouriteVacanciesUseCase().collect { data ->
                _uiState.update {
                    val vacanciesData = vacanciesMapper(data)
                    it.copy(
                        vacancies = vacanciesData.vacancyItems,
                        vacanciesCountText = vacanciesData.allVacanciesText
                    )
                }
            }
        }
    }

    fun onVacancyApplyBtnClick(vacancy: VacancyItem) {

    }

    fun onVacancyFavouriteBtnClick(vacancy: VacancyItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                changeVacancyIsFavouriteUseCase(vacancy.id, !vacancy.isFavorite)
            }
        }
    }
}
