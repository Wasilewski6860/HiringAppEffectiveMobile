package ru.hiringapp.search.interactor.api.source.vacancy

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.dto.response.VacanciesResponse

internal interface VacancyRemoteDataSource {

    suspend fun getVacancies(): OperationResult<VacanciesResponse>
}