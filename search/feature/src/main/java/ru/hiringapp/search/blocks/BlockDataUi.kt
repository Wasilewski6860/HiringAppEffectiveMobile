package ru.hiringapp.search.blocks

import android.graphics.Rect
import androidx.annotation.ColorRes
import ru.hiringapp.base_feature.extensions.dp
import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem
import ru.hiringapp.base_feature.itemdecoration.ItemRect

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

internal sealed class BlockDataUi : DecoratedRecyclerViewItem() {

    override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
        return super.areContentsTheSame(newItem) ||
                newItem is BlockDataUi && newItem == this
    }

    data class OfferItem(
        val iconState: OfferIconState,
        val title: String,
        val buttonText: String?
    ) : BlockDataUi() {

        override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean {
            return super.areItemsTheSame(newItem) ||
                    newItem is OfferItem && newItem.title == title
        }

        override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areContentsTheSame(newItem) && newItem is OfferItem
    }

    data class OffersItem(
        val offers: List<OfferItem>,
    ) : BlockDataUi() {
        override var decorationData: DecorationData = DecorationData(
            outsideMargins = ItemRect(16.dp,0, 0, 0)
        )

        override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areItemsTheSame(newItem) || newItem is OffersItem

        override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areContentsTheSame(newItem) && newItem == this
    }

    class VacanciesForYouItem : BlockDataUi() {
        override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areItemsTheSame(newItem) || newItem is VacanciesForYouItem

        override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areContentsTheSame(newItem) && newItem == this
    }

    data class VacanciesItem(
        val vacancies: List<DecoratedRecyclerViewItem>,
    ) : BlockDataUi() {
        override var decorationData: DecorationData = DecorationData(
            outRect = Rect(16.dp, 0, 16.dp, 20.dp)
        )

        override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areItemsTheSame(newItem) || newItem is VacanciesItem

        override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areContentsTheSame(newItem) && newItem == this
    }

    data class LoadMoreItem(
        val count: Int
    ) : BlockDataUi() {
        override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areItemsTheSame(newItem) || newItem is LoadMoreItem

        override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areContentsTheSame(newItem) && newItem == this
    }

    data class VacanciesInfoItem(
        val count: Int
    ) : BlockDataUi() {
        override fun areItemsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areItemsTheSame(newItem) || newItem is VacanciesInfoItem

        override fun areContentsTheSame(newItem: DecoratedRecyclerViewItem): Boolean =
            super.areContentsTheSame(newItem) && newItem == this
    }
}
