package ru.hiringapp.search.blocks.offers

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.hiringapp.base_feature.extensions.decoratedAdapterDiffUtils
import ru.hiringapp.search.blocks.BlockDataUi

internal fun createOffersAdapter(
    onOfferItemClick: (item: BlockDataUi.OfferItem) -> Unit,
): AsyncListDifferDelegationAdapter<BlockDataUi.OfferItem> =
    AsyncListDifferDelegationAdapter(
        decoratedAdapterDiffUtils(),
        createOfferDelegate(onOfferItemClick)
    )