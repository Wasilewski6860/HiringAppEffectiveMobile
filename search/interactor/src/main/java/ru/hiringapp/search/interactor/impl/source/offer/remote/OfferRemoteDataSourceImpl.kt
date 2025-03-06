package ru.hiringapp.search.interactor.impl.source.offer.remote

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.network.mapToOperationResult
import ru.hiringapp.search.interactor.api.source.offer.OfferRemoteDataSource
import ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response.OffersResponse
import javax.inject.Inject

internal class OfferRemoteDataSourceImpl @Inject constructor(
    private val offersApi: OfferApi
): OfferRemoteDataSource {

    override suspend fun getOffers(): OperationResult<OffersResponse> {
        return offersApi.getOffers().mapToOperationResult()
    }
}