package ru.hiringapp.offers.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import ru.hiringapp.offers.api.repository.OfferRepository
import ru.hiringapp.offers.api.source.OfferLocalDataSource
import ru.hiringapp.offers.api.source.OfferRemoteDataSource
import ru.hiringapp.offers.impl.repository.OfferRepositoryImpl
import ru.hiringapp.offers.impl.source.local.OfferLocalDataSourceImpl
import ru.hiringapp.offers.impl.source.remote.OfferApi
import ru.hiringapp.offers.impl.source.remote.OfferRemoteDataSourceImpl
import ru.hiringapp.offers.impl.source.remote.dto.response.ButtonDto
import ru.hiringapp.offers.impl.source.remote.dto.response.OfferDto
import ru.hiringapp.offers.impl.source.remote.dto.response.OffersResponse
import ru.hiringapp.offers.impl.usecase.GetOffersUseCaseImpl
import ru.hiringapp.offers.impl.usecase.ObserveOffersUseCaseIMpl
import ru.hiringapp.offers.api.usecase.GetOffersUseCase
import ru.hiringapp.offers.api.usecase.ObserveOffersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindObserveOffersUseCase(
        useCaseImpl: ObserveOffersUseCaseIMpl,
    ): ObserveOffersUseCase

    @Binds
    fun bindGetOffersUseCase(
        useCaseImpl: GetOffersUseCaseImpl,
    ): GetOffersUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindOfferRepository(
        repositoryImpl: OfferRepositoryImpl,
    ): OfferRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    fun bindOfferLocalDataSource(
        localSourceImpl: OfferLocalDataSourceImpl,
    ): OfferLocalDataSource

    @Binds
    fun bindOfferRemoteDataSource(
        remoteSourceImpl: OfferRemoteDataSourceImpl,
    ): OfferRemoteDataSource

}

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

//    @Provides
//    @Singleton
//    fun provideOfferApi(retrofit: Retrofit): OfferApi {
//        return retrofit.create(OfferApi::class.java)
//    }

    @Provides
    @Singleton
    fun provideOfferApi(retrofit: Retrofit): OfferApi {
        val offers = listOf(
            OfferDto(
                id = "near_vacancies",
                title = "Вакансии рядом с вами",
                description = "", // Описание отсутствует в JSON, поэтому оставляем пустым
                button = null, // Кнопка отсутствует
                link = "https://hh.ru/"
            ), OfferDto(
                id = "level_up_resume",
                title = "Поднять резюме в поиске",
                description = "", // Описание отсутствует в JSON
                button = ButtonDto(text = "Поднять"), // Кнопка присутствует
                link = "https://hh.ru/mentors?from=footer_new&hhtmFromLabel=footer_new&hhtmFrom=main&purposeId=1"
            ), OfferDto(
                id = "temporary_job",
                title = "Временная работа или подработка",
                description = "", // Описание отсутствует в JSON
                button = null, // Кнопка отсутствует
                link = "https://hh.ru/"
            ), OfferDto(
                id = null, // ID отсутствует в JSON
                title = "Полезные статьи и советы", description = "", // Описание отсутствует в JSON
                button = null, // Кнопка отсутствует
                link = "https://hh.ru/articles?hhtmFrom=main"
            )
        )
        return object : OfferApi {
            override suspend fun getOffers(): Response<OffersResponse> {
                return Response.success(
                    OffersResponse(
                        offers = offers
                    )
                )
            }

        }

    }

}