package ru.hiringapp.vacancy.impl.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.transport.base.transform
import ru.hiringapp.vacancy.api.source.VacancyLocalDataSource
import ru.hiringapp.vacancy.api.source.VacancyRemoteDataSource
import javax.inject.Inject

internal class VacancyRepositoryImpl @Inject constructor(
    private val vacanciesLocalDataSource: VacancyLocalDataSource,
    private val vacanciesRemoteDataSource: VacancyRemoteDataSource,
) : VacancyRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndSaveVacancies()
        }
    }

    override suspend fun getVacancies(): List<Vacancy> {
        when (val remoteResult = vacanciesRemoteDataSource.getVacancies()) {
            is OperationResult.Success -> {
                val vacancies = remoteResult.data.vacancies
                if (vacancies.isNotEmpty()) {
                    val vacanciesMapped = vacancies.transform()
                    vacanciesLocalDataSource.clearVacancies()
                    vacanciesLocalDataSource.saveVacancies(vacanciesMapped)
                    return vacanciesMapped
                }
                return emptyList()
            }
            else -> {
                return emptyList()
            }
        }
    }

    override fun observeVacancies(): Flow<List<Vacancy>> =
        vacanciesLocalDataSource.observeVacancies()

    override fun observeFavouriteVacanciesCount(): Flow<Int> =
        vacanciesLocalDataSource.observeFavouriteVacanciesCount()


    override fun observeFavouriteVacancies(): Flow<List<Vacancy>> =
        vacanciesLocalDataSource.observeFavouriteVacancies()

    override suspend fun changeVacancyIsFavourite(id: String, isFavourite: Boolean) =
        vacanciesLocalDataSource.changeVacancyIsFavourite(id, isFavourite)

    private suspend fun fetchAndSaveVacancies() {
        when (val remoteResult = vacanciesRemoteDataSource.getVacancies()) {
            is OperationResult.Success -> {
                val vacancies = remoteResult.data.vacancies
                if (vacancies.isNotEmpty()) {
                    vacanciesLocalDataSource.clearVacancies()
                    vacanciesLocalDataSource.saveVacancies(vacancies.transform())
                }
            }

            else -> {
                Unit
            }
        }
    }

}


