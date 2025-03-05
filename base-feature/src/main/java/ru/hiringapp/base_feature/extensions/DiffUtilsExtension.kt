package ru.hiringapp.base_feature.extensions

import androidx.recyclerview.widget.DiffUtil
import ru.hiringapp.base_feature.itemdecoration.DecoratedRecyclerViewItem

fun <T : DecoratedRecyclerViewItem> decoratedAdapterDiffUtils(): DiffUtil.ItemCallback<T> =
    DecoratedDiffUtil()

open class DecoratedDiffUtil<ItemType : DecoratedRecyclerViewItem> :
    DiffUtil.ItemCallback<ItemType>() {

    override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean =
        oldItem.areContentsTheSame(newItem)
}