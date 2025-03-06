package ru.hiringapp.search.blocks.offers

import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.search.blocks.BlockDataUi
import ru.hiringapp.search.blocks.BlocksMapper
import ru.hiringapp.search.blocks.OfferIconState
import javax.inject.Inject

internal class OffersMapper @Inject constructor() :
        (List<Offer>) -> BlockDataUi.OffersItem {

    override fun invoke(
        data: List<Offer>,
    ): BlockDataUi.OffersItem {
        return BlockDataUi.OffersItem(
            offers = data.map {
                BlockDataUi.OfferItem(
                    iconState = OfferIconState.fromString(it.id),
                    title = it.title,
                    buttonText = it.button?.text
                )
            }
        )
    }
}