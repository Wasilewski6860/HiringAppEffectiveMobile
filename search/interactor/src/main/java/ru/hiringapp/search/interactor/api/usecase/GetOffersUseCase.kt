package ru.hiringapp.search.interactor.api.usecase

import ru.hiringapp.domain.offer.Offer

fun interface GetOffersUseCase {
    suspend operator fun invoke(): List<Offer>
}