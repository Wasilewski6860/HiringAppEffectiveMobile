package ru.hiringapp.search.interactor.impl.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.source.offer.OfferLocalDataSource
import ru.hiringapp.search.interactor.api.source.offer.OfferRemoteDataSource
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyLocalDataSource
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyRemoteDataSource
import ru.hiringapp.transport.base.transform
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val offerLocalDataSource: OfferLocalDataSource,
    private val offerRemoteDataSource: OfferRemoteDataSource,
    private val vacanciesLocalDataSource: VacancyLocalDataSource,
    private val vacanciesRemoteDataSource: VacancyRemoteDataSource,
) : SearchRepository {

//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            fetchAndSaveOffers()
////            fetchAndSaveVacancies()
//        }
//    }

    override suspend fun getOffers(): List<Offer> {
        when (val remoteResult = offerRemoteDataSource.getOffers()) {
            is OperationResult.Success -> {
                val offersDto = remoteResult.data.offers
                val offers = offersDto.transform()
                offerLocalDataSource.clearOffers()
                offerLocalDataSource.saveOffers(offers)
                return offers
            }
            is OperationResult.EmptyResult -> {
                val offersDto = offerLocalDataSource.getOffers()
                return offersDto
            }
            else -> {
                return emptyList()
            }
        }
    }

    override fun observeOffers(): Flow<List<Offer>> =
        offerLocalDataSource.observeOffers()

    override fun observeVacancies(): Flow<List<Vacancy>> =
        vacanciesLocalDataSource.observeVacancies()

    override fun observeFavouriteVacancies(): Flow<List<Vacancy>> =
        vacanciesLocalDataSource.observeFavouriteVacancies()

    override suspend fun changeVacancyIsFavourite(id: String, isFavourite: Boolean) =
        vacanciesLocalDataSource.changeVacancyIsFavourite(id, isFavourite)

    private suspend fun fetchAndSaveOffers() {
        when (val remoteResult = offerRemoteDataSource.getOffers()) {
            is OperationResult.Success -> {
                val offers = remoteResult.data.offers
                if (offers.isNotEmpty()) {
                    offerLocalDataSource.clearOffers()
                    offerLocalDataSource.saveOffers(offers.transform())
                }
            }

            else -> Unit //TODO Добавить обработку ошибок
        }
    }

    private suspend fun fetchAndSaveVacancies() {
        when (val remoteResult = vacanciesRemoteDataSource.getVacancies()) {
            is OperationResult.Success -> {
                val vacancies = remoteResult.data.vacancies
                if (vacancies.isNotEmpty()) {
                    vacanciesLocalDataSource.clearVacancies()
                    vacanciesLocalDataSource.saveVacancies(vacancies.transform())
                }
            }

            else -> Unit //TODO Добавить обработку ошибок
        }
    }

}


