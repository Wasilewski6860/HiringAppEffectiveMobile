package ru.hiringapp.search.mapper

import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.offers.OfferIconState
import ru.hiringapp.offers.OfferItem
import javax.inject.Inject

internal class OffersMapper @Inject constructor() :
        (List<Offer>) -> List<OfferItem> {

    override fun invoke(
        data: List<Offer>,
    ): List<OfferItem> {
        return data.map {
            OfferItem(
                iconState = OfferIconState.fromString(it.id),
                title = it.title,
                buttonText = it.button?.text,
                link = it.link
            )
        }
    }
}