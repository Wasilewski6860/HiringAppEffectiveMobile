package ru.hiringapp.vacancy

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

fun createVacanciesAdapter(
    onVacancyApplyButtonItemClick: (item: VacancyItem) -> Unit,
    onVacancyFavouriteButtonItemClick: (item: VacancyItem) -> Unit,
): AsyncListDifferDelegationAdapter<VacancyItem> =
    AsyncListDifferDelegationAdapter(
        object : DiffUtil.ItemCallback<VacancyItem>() {
            override fun areItemsTheSame(oldItem: VacancyItem, newItem: VacancyItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: VacancyItem, newItem: VacancyItem): Boolean {
                return oldItem == newItem
            }
        },
        createVacancyDelegate(onVacancyApplyButtonItemClick, onVacancyFavouriteButtonItemClick)
    )