package ru.hiringapp.vacancy

import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem

data class VacancyItem(
    val id: String,
    val lookingNumberText: String?,
    val isFavorite: Boolean,
    val title: String,
    val address: String,
    val company: String,
    val experience: String,
    val publishedDate: String
) : DecoratedRecyclerViewItem() {

    override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return super.areItemsTheSame(newItem) ||
                newItem is VacancyItem && newItem.title == title
    }

    override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is VacancyItem
}