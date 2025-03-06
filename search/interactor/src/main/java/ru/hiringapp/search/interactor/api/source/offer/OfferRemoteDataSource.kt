package ru.hiringapp.search.interactor.api.source.offer

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response.OffersResponse

internal interface OfferRemoteDataSource {

    suspend fun getOffers(): OperationResult<OffersResponse>
}