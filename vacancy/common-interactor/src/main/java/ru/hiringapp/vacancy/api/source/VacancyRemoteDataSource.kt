package ru.hiringapp.vacancy.api.source

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.vacancy.impl.source.remote.dto.response.VacanciesResponse

internal interface VacancyRemoteDataSource {

    suspend fun getVacancies(): OperationResult<VacanciesResponse>
}