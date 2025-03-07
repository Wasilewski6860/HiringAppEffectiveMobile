package ru.hiringapp.offers.impl.source.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.transport.base.Transformable

@Serializable
class OfferDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("button")
    val button: ButtonDto? = null,
    @SerialName("link")
    val link: String
) : Transformable<Offer> {
    override fun transform(): Offer {
        return Offer(
            id = id,
            title = title,
            description = description,
            link = link,
            button = button?.transform()
        )
    }
}