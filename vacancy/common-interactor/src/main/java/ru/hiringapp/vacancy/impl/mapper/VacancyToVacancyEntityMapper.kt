package ru.hiringapp.vacancy.impl.mapper

import ru.hiringapp.base.mapper.Mapper
import ru.hiringapp.database.entity.VacancyEntity
import ru.hiringapp.domain.vacancy.Vacancy
import javax.inject.Inject

class VacancyToVacancyEntityMapper @Inject constructor() : Mapper<Vacancy, VacancyEntity> {
    override fun map(type: Vacancy): VacancyEntity {
        with(type) {
            return VacancyEntity(
                id = id,
                title = title,
                company = company,
                address = address,
                experience = experience,
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
}