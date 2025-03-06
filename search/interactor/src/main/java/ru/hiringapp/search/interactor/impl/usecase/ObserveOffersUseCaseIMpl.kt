package ru.hiringapp.search.interactor.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.usecase.ObserveOffersUseCase
import javax.inject.Inject

internal class ObserveOffersUseCaseIMpl @Inject constructor(
    private val searchRepository: SearchRepository,
) : ObserveOffersUseCase {
    override fun invoke(): Flow<List<Offer>> = searchRepository.observeOffers()
}