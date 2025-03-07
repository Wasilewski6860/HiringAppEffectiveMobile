package ru.hiringapp.offers

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.hiringapp.base_feature.extensions.decoratedAdapterDiffUtils

fun createOffersAdapter(
    onOfferItemClick: (item: OfferItem) -> Unit,
): AsyncListDifferDelegationAdapter<OfferItem> =
    AsyncListDifferDelegationAdapter(
        decoratedAdapterDiffUtils(),
        createOfferDelegate(onOfferItemClick)
    )