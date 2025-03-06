package ru.hiringapp.search.interactor.impl.source.vacancy.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.vacancy.Salary
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.transport.base.Transformable

@Serializable
class VacancyDto(
    @SerialName("id")
    val id: String,
    @SerialName("id")
    val title: String, //Needed
    @SerialName("id")
    val company: String, //Needed
    @SerialName("id")
    val address: AddressDto, //Needed just address.town
    @SerialName("id")
    val experience: ExperienceDto, //Needed just experience.previewText
    @SerialName("id")
    val publishedDate: String, //Needed
    @SerialName("id")
    val isFavorite: Boolean, //Needed
    @SerialName("id")
    val salary: Salary,
    @SerialName("id")
    val schedules: List<String>,
    @SerialName("id")
    val description: String,
    @SerialName("id")
    val responsibilities: String,
    @SerialName("id")
    val questions: List<String>,
    @SerialName("id")
    val lookingNumber: Int? = null, //Needed
    @SerialName("id")
    val appliedNumber: Int? = null
) : Transformable<Vacancy> {
    override fun transform(): Vacancy {
        return Vacancy(
            id = id,
            title = title,
            company = company,
            address = address.transform(),
            experience = experience.transform(),
            publishedDate = publishedDate,
            isFavorite = isFavorite,
            salary = salary,
            schedules = schedules,
            description = description,
            responsibilities = responsibilities,
            questions = questions,
            lookingNumber = lookingNumber,
            appliedNumber = appliedNumber
        )
    }
}