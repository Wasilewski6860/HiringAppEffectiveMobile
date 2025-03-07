package ru.hiringapp.offers.impl.source.remote

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.network.mapToOperationResult
import ru.hiringapp.offers.api.source.OfferRemoteDataSource
import ru.hiringapp.offers.impl.source.remote.dto.response.OffersResponse
import javax.inject.Inject

internal class OfferRemoteDataSourceImpl @Inject constructor(
    private val offersApi: OfferApi
) : OfferRemoteDataSource {

    override suspend fun getOffers(): OperationResult<OffersResponse> {
        return offersApi.getOffers().mapToOperationResult()
    }
}