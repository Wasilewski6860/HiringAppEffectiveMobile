package ru.hiringapp.offers.impl.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.offers.api.repository.OfferRepository
import ru.hiringapp.offers.api.source.OfferLocalDataSource
import ru.hiringapp.offers.api.source.OfferRemoteDataSource
import ru.hiringapp.transport.base.transform
import javax.inject.Inject

internal class OfferRepositoryImpl @Inject constructor(
    private val offerLocalDataSource: OfferLocalDataSource,
    private val offerRemoteDataSource: OfferRemoteDataSource
) : OfferRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndSaveOffers()
        }
    }

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

}


