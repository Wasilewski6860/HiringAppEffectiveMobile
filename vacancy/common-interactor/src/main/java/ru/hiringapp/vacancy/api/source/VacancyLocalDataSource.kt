package ru.hiringapp.vacancy.api.source

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy

interface VacancyLocalDataSource {

    suspend fun clearVacancies()

    suspend fun saveVacancies(vacancies: List<Vacancy>)

    suspend fun changeVacancyIsFavourite(id: String, isFavourite: Boolean)

    fun observeVacancies(): Flow<List<Vacancy>>

    fun observeFavouriteVacancies(): Flow<List<Vacancy>>

    fun observeFavouriteVacanciesCount(): Flow<Int>
}