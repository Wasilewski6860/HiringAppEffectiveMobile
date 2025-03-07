package ru.hiringapp.vacancy.impl.source.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hiringapp.domain.vacancy.Salary
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.transport.base.Transformable
import ru.hiringapp.vacancy.impl.source.remote.dto.response.AddressDto
import ru.hiringapp.vacancy.impl.source.remote.dto.response.ExperienceDto

@Serializable
class VacancyDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String, //Needed
    @SerialName("company")
    val company: String, //Needed
    @SerialName("address")
    val address: AddressDto, //Needed just address.town
    @SerialName("experience")
    val experience: ExperienceDto, //Needed just experience.previewText
    @SerialName("publishedDate")
    val publishedDate: String, //Needed
    @SerialName("isFavorite")
    val isFavorite: Boolean, //Needed
    @SerialName("salary")
    val salary: Salary,
    @SerialName("schedules")
    val schedules: List<String>,
    @SerialName("description")
    val description: String,
    @SerialName("responsibilities")
    val responsibilities: String,
    @SerialName("questions")
    val questions: List<String>,
    @SerialName("lookingNumber")
    val lookingNumber: Int? = null, //Needed
    @SerialName("appliedNumber")
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