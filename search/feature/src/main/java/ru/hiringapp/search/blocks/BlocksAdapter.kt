package ru.hiringapp.search.blocks

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.hiringapp.base_feature.extensions.decoratedAdapterDiffUtils
import ru.hiringapp.search.blocks.offers.createOffersDelegate

internal fun createBlocksAdapter(
    onOfferClick: (BlockDataUi.OfferItem) -> Unit,
): AsyncListDifferDelegationAdapter<BlockDataUi> = AsyncListDifferDelegationAdapter(
    decoratedAdapterDiffUtils(),
    createOffersDelegate(onOfferClick)
)
