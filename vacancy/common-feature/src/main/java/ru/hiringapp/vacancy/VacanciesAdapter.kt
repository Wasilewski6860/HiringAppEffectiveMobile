package ru.hiringapp.vacancy

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.hiringapp.base_feature.extensions.decoratedAdapterDiffUtils

fun createVacanciesAdapter(
    onVacancyApplyButtonItemClick: (item: VacancyItem) -> Unit,
    onVacancyFavouriteButtonItemClick: (item: VacancyItem) -> Unit,
): AsyncListDifferDelegationAdapter<VacancyItem> =
    AsyncListDifferDelegationAdapter(
        decoratedAdapterDiffUtils(),
        createVacancyDelegate(onVacancyApplyButtonItemClick, onVacancyFavouriteButtonItemClick)
    )