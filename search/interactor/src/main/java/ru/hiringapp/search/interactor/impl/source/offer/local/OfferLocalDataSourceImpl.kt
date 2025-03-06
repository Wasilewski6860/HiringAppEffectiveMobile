package ru.hiringapp.search.interactor.impl.source.offer.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.hiringapp.base.qualifier.IoDispatcher
import ru.hiringapp.database.dao.OfferDao
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.search.interactor.api.source.offer.OfferLocalDataSource
import ru.hiringapp.search.interactor.impl.mapper.OfferToOfferEntityMapper
import ru.hiringapp.transport.base.transform
import javax.inject.Inject

internal class OfferLocalDataSourceImpl @Inject constructor(
    private val offerDao: OfferDao,
    private val mapper: OfferToOfferEntityMapper
) : OfferLocalDataSource {

    override suspend fun clearOffers() {
        offerDao.deleteTable()
    }

    override suspend fun saveOffers(offers: List<Offer>) {
        offerDao.insertOffers(offers.map { mapper.map(it) })
    }

    override fun observeOffers(): Flow<List<Offer>> =
        offerDao.getOffersFlow().map { it.transform() }
}