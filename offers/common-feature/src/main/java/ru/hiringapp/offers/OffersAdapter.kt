package ru.hiringapp.offers

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

fun createOffersAdapter(
    onOfferItemClick: (item: OfferItem) -> Unit,
): AsyncListDifferDelegationAdapter<OfferItem> =
    AsyncListDifferDelegationAdapter(
        object : DiffUtil.ItemCallback<OfferItem>() {
            override fun areItemsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: OfferItem, newItem: OfferItem): Boolean {
                return oldItem == newItem
            }
        },
        createOfferDelegate(onOfferItemClick)
    )