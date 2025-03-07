package ru.hiringapp.favourites.data

import ru.hiringapp.base_feature.mvvm.State
import ru.hiringapp.vacancy.VacancyItem

data class FavouritesUiState(
    val vacancies: List<VacancyItem> = listOf(),
    val vacanciesCountText: String = ""
): State