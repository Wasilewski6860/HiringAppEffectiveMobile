package ru.hiringapp.offers.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.offers.api.repository.OfferRepository
import ru.hiringapp.offers.api.usecase.ObserveOffersUseCase
import javax.inject.Inject

internal class ObserveOffersUseCaseIMpl @Inject constructor(
    private val offerRepository: OfferRepository,
) : ObserveOffersUseCase {
    override fun invoke(): Flow<List<Offer>> = offerRepository.observeOffers()
}