package ru.hiringapp.offers.api.source

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.offers.impl.source.remote.dto.response.OffersResponse

internal interface OfferRemoteDataSource {

    suspend fun getOffers(): OperationResult<OffersResponse>
}