package ru.hiringapp.vacancy.api.repository

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy

internal interface VacancyRepository {

    suspend fun getVacancies(): List<Vacancy>

    fun observeVacancies(): Flow<List<Vacancy>>

    fun observeFavouriteVacanciesCount(): Flow<Int>

    fun observeFavouriteVacancies(): Flow<List<Vacancy>>

    suspend fun changeVacancyIsFavourite(id: String, isFavourite: Boolean)

}
