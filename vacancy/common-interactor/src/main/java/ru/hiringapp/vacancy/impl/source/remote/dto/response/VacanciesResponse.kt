package ru.hiringapp.vacancy.impl.source.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.transport.base.Transformable

@Serializable
class VacanciesResponse(
    @SerialName("vacancies") val vacancies: List<VacancyDto>
) : Transformable<List<Vacancy>> {
    override fun transform(): List<Vacancy> =
        vacancies.map { it.transform() }
}