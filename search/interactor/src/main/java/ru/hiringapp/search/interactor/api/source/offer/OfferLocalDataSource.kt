package ru.hiringapp.search.interactor.api.source.offer

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.domain.offer.Offer

interface OfferLocalDataSource {

    suspend fun clearOffers()

    suspend fun saveOffers(offers: List<Offer>)

    fun observeOffers(): Flow<List<Offer>>

    suspend fun getOffers(): List<Offer>
}