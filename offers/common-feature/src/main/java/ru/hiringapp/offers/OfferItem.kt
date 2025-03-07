package ru.hiringapp.offers

import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem

enum class OfferIconState(val id: String) {
    NEAR_VACANCIES("near_vacancies"),
    LEVEL_UP_RESUME("level_up_resume"),
    TEMPORARY_JOB("temporary_job"),
    NONE("");

    companion object {
        fun fromString(value: String?): OfferIconState {
            return entries.find { it.id == value }
                ?: NONE
        }
    }
}

data class OfferItem(
    val iconState: OfferIconState,
    val title: String,
    val buttonText: String?
) : DecoratedRecyclerViewItem() {

    override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return super.areItemsTheSame(newItem) ||
                newItem is OfferItem && newItem.title == title
    }

    override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
        super.areContentsTheSame(newItem) && newItem is OfferItem
}