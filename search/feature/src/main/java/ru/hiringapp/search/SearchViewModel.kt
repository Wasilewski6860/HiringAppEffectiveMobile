package ru.hiringapp.search

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
import ru.hiringapp.base_feature.navigation.ExternalUrlRoute
import ru.hiringapp.base_feature.navigation.NavigationManager
import ru.hiringapp.offers.OfferItem
import ru.hiringapp.search.mapper.VacanciesMapper
import ru.hiringapp.search.data.SearchUiEvent
import ru.hiringapp.search.data.SearchUiState
import ru.hiringapp.offers.api.usecase.ObserveOffersUseCase
import ru.hiringapp.vacancy.api.usecase.ChangeVacancyIsFavouriteUseCase
import ru.hiringapp.vacancy.api.usecase.ObserveVacanciesUseCase
import ru.hiringapp.search.mapper.OffersMapper
import ru.hiringapp.vacancy.VacancyItem
import ru.hiringapp.vacancy.api.usecase.UpdateVacanciesUseCase
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val updateVacanciesUseCase: UpdateVacanciesUseCase,
    private val observeOffersUseCase: ObserveOffersUseCase,
    private val observeVacanciesUseCase: ObserveVacanciesUseCase,
    private val changeVacancyIsFavouriteUseCase: ChangeVacancyIsFavouriteUseCase,
    private val offersMapper: OffersMapper,
    private val vacanciesMapper: VacanciesMapper,
    private val navigationManager: NavigationManager,
) : BaseViewModel<SearchUiState, SearchUiEvent>() {

    override val _uiState = MutableStateFlow(SearchUiState())
    override val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<SearchUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    init {
        subscribeOnOffers()
        subscribeOnVacancies()
    }

    private fun subscribeOnOffers() {
        viewModelScope.launch {
            observeOffersUseCase().collect { data ->
                _uiState.update {
                    it.copy(offers = offersMapper(data))
                }
            }
        }
    }

    private fun subscribeOnVacancies() {
        viewModelScope.launch {
            observeVacanciesUseCase().collect { data ->
                _uiState.update {
                    val vacanciesData = vacanciesMapper(it.isExpanded, data)
                    it.copy(
                        vacancies = vacanciesData.vacancyItems,
                        allVacanciesText = vacanciesData.allVacanciesText,
                        additionalVacanciesText = vacanciesData.additionalVacanciesText
                    )
                }
            }
        }
    }

    fun onOfferClick(offer: OfferItem) {
        navigationManager.navigateTo(ExternalUrlRoute(offer.link))
    }

    fun onVacancyApplyBtnClick(vacancy: VacancyItem) {

    }

    fun onActionBackClick() {
        _uiState.update {
            it.copy(isExpanded = !it.isExpanded)
        }
    }

    fun onVacancyFavouriteBtnClick(vacancy: VacancyItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                changeVacancyIsFavouriteUseCase(vacancy.id, !vacancy.isFavorite)
            }
        }
    }

    fun onLoadMoreBtnClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val vacancies = updateVacanciesUseCase()
            _uiState.update {
                val vacanciesData = vacanciesMapper(true, vacancies)
                it.copy(
                    isExpanded = true,
                    vacancies = vacanciesData.vacancyItems,
                    allVacanciesText = vacanciesData.allVacanciesText,
                    additionalVacanciesText = vacanciesData.additionalVacanciesText
                )
            }
        }
    }
}
