package ru.hiringapp.search.interactor.impl.source.vacancy.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.vacancy.Experience
import ru.hiringapp.transport.base.Transformable

@Serializable
data class ExperienceDto(
    @SerialName("previewText")
    val previewText: String,
    @SerialName("text")
    val text: String
) : Transformable<Experience> {
    override fun transform(): Experience {
        return Experience(
            previewText = previewText,
            text = text
        )
    }
}