package ru.hiringapp.offers.impl.source.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.hiringapp.offers.impl.source.remote.OffersUrls.OFFERS
import ru.hiringapp.offers.impl.source.remote.dto.response.OffersResponse

internal interface OfferApi {

    @GET(OFFERS)
    suspend fun getOffers(): Response<OffersResponse>

}