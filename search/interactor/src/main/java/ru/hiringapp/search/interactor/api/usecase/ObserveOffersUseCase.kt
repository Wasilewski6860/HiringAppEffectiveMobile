package ru.hiringapp.search.interactor.api.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.offer.Offer

fun interface ObserveOffersUseCase {
    operator fun invoke(): Flow<List<Offer>>
}