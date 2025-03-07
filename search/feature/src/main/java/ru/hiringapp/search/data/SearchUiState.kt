package ru.hiringapp.search.data

import ru.hiringapp.base_feature.mvvm.State
import ru.hiringapp.offers.OfferItem
import ru.hiringapp.vacancy.VacancyItem

internal data class SearchUiState(
    val offers: List<OfferItem> = listOf(),
    val vacancies: List<VacancyItem> = listOf(),
    val isExpanded: Boolean = false,
    val allVacanciesText: String = "",
    val additionalVacanciesText: String = ""
) : State