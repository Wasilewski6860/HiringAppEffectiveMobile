package ru.hiringapp.search.interactor.impl.usecase

import kotlinx.coroutines.flow.Flow
import ru.hiringapp.domain.vacancy.Vacancy
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.usecase.ObserveVacanciesUseCase
import javax.inject.Inject

internal class ObserveVacanciesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
) : ObserveVacanciesUseCase {
    override fun invoke(): Flow<List<Vacancy>> = searchRepository.observeVacancies()
}