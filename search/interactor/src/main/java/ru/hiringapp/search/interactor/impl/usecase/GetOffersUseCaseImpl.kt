package ru.hiringapp.search.interactor.impl.usecase

import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.usecase.GetOffersUseCase
import javax.inject.Inject

internal class GetOffersUseCaseImpl @Inject constructor(private val searchRepository: SearchRepository): GetOffersUseCase {
    override suspend fun invoke(): List<Offer> {
        return searchRepository.getOffers()
    }
}