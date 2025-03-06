package ru.hiringapp.search.interactor.impl.source.vacancy.remote

import ru.hiringapp.base.result.OperationResult
import ru.hiringapp.network.mapToOperationResult
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyRemoteDataSource
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.dto.response.VacanciesResponse
import javax.inject.Inject

internal class VacancyRemoteDataSourceImpl @Inject constructor(
    private val vacancyApi: VacancyApi
) : VacancyRemoteDataSource {

    override suspend fun getVacancies(): OperationResult<VacanciesResponse> =
        vacancyApi.getVacancies().mapToOperationResult()
}