package ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.offer.Button
import ru.hiringapp.transport.base.Transformable

@Serializable
data class ButtonDto(
    @SerialName("text")
    val text: String
) : Transformable<Button> {
    override fun transform(): Button {
        return Button(
            text = text
        )
    }

}