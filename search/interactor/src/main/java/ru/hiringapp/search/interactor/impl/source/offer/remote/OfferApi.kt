package ru.hiringapp.search.interactor.impl.source.offer.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.hiringapp.search.interactor.impl.source.offer.remote.OffersUrls.OFFERS
import ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response.OffersResponse

internal interface OfferApi {

    @GET(OFFERS)
    suspend fun getOffers(): Response<OffersResponse>

}