package ru.hiringapp.search.interactor.api.repository

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.offer.Offer
import ru.hiringapp.domain.vacancy.Vacancy

internal interface SearchRepository {

    suspend fun getOffers(): List<Offer>

    fun observeOffers(): Flow<List<Offer>>

    fun observeVacancies(): Flow<List<Vacancy>>

    fun observeFavouriteVacancies(): Flow<List<Vacancy>>

    suspend fun changeVacancyIsFavourite(id: String, isFavourite: Boolean)

}
