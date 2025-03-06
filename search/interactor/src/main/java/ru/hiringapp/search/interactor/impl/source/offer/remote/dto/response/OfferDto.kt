package ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.offer.Button
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.transport.base.Transformable

@Serializable
class OfferDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("link")
    val link: String,
    @SerialName("button")
    val button: ButtonDto? = null
) : Transformable<Offer> {
    override fun transform(): Offer {
        return Offer(
            id = id,
            title = title,
            link = link,
            button = button?.transform()
        )
    }
}