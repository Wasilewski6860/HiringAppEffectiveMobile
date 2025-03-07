package ru.hiringapp.offers.api.usecase

import ru.hiringapp.domain.offer.Offer

fun interface GetOffersUseCase {
    suspend operator fun invoke(): List<Offer>
}