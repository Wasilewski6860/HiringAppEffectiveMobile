package ru.hiringapp.offers.impl.usecase

import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.offers.api.repository.OfferRepository
import ru.hiringapp.offers.api.usecase.GetOffersUseCase
import javax.inject.Inject

internal class GetOffersUseCaseImpl @Inject constructor(private val offerRepository: OfferRepository) :
    GetOffersUseCase {
    override suspend fun invoke(): List<Offer> {
        return offerRepository.getOffers()
    }
}