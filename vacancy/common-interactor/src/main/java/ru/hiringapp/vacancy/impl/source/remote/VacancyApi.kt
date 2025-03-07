package ru.hiringapp.vacancy.impl.source.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.hiringapp.vacancy.impl.source.remote.VacanciesUrls.VACANCIES
import ru.hiringapp.vacancy.impl.source.remote.dto.response.VacanciesResponse

internal interface VacancyApi {

    @GET(VACANCIES)
    suspend fun getVacancies(): Response<VacanciesResponse>

}