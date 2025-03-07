package ru.hiringapp.offers.impl.source.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.transport.base.Transformable

@Serializable
class OffersResponse(
    @SerialName("offers") val offers: List<OfferDto>
) : Transformable<List<Offer>> {
    override fun transform(): List<Offer> =
        offers.map { it.transform() }
}