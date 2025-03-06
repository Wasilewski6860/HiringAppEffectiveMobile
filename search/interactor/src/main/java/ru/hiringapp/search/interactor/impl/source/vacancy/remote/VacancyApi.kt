package ru.hiringapp.search.interactor.impl.source.vacancy.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.VacanciesUrls.VACANCIES
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.dto.response.VacanciesResponse

internal interface VacancyApi {

    @GET(VACANCIES)
    suspend fun getVacancies(): Response<VacanciesResponse>

}