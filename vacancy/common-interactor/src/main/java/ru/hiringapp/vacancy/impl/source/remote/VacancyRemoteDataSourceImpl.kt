package ru.hiringapp.vacancy.impl.source.remote

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.network.mapToOperationResult
import ru.hiringapp.vacancy.impl.source.remote.dto.response.VacanciesResponse
import ru.hiringapp.vacancy.api.source.VacancyRemoteDataSource
import javax.inject.Inject

internal class VacancyRemoteDataSourceImpl @Inject constructor(
    private val vacancyApi: VacancyApi
) : VacancyRemoteDataSource {

    override suspend fun getVacancies(): OperationResult<VacanciesResponse> =
        vacancyApi.getVacancies().mapToOperationResult()
}