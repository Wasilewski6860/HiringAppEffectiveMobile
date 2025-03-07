package ru.hiringapp.vacancy.impl.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.hiringapp.database.dao.VacancyDao
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.impl.mapper.VacancyToVacancyEntityMapper
import ru.hiringapp.transport.base.transform
import ru.hiringapp.vacancy.api.source.VacancyLocalDataSource
import javax.inject.Inject

internal class VacancyLocalDataSourceImpl @Inject constructor(
    private val vacancyDao: VacancyDao,
    private val mapper: VacancyToVacancyEntityMapper
) : VacancyLocalDataSource {

    override suspend fun clearVacancies() {
        vacancyDao.deleteTable()
    }

    override suspend fun saveVacancies(vacancies: List<Vacancy>) {
        vacancyDao.insertVacancies(vacancies.map { mapper.map(it) })
    }

    override suspend fun changeVacancyIsFavourite(id: String, isFavourite: Boolean) {
        vacancyDao.updateFavoriteStatus(id, isFavourite)
    }

    override fun observeVacancies(): Flow<List<Vacancy>> =
        vacancyDao.getVacanciesFlow().map { it.transform() }


    override fun observeFavouriteVacancies(): Flow<List<Vacancy>> =
        vacancyDao.getFavouriteVacanciesFlow().map { it.transform() }

    override fun observeFavouriteVacanciesCount(): Flow<Int> =
        vacancyDao.observeFavouriteVacanciesCount()


}