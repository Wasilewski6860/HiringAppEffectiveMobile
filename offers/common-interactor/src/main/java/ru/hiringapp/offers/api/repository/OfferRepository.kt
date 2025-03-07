package ru.hiringapp.offers.api.repository

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.offer.Offer

internal interface OfferRepository {
    suspend fun getOffers(): List<Offer>
    fun observeOffers(): Flow<List<Offer>>
}
