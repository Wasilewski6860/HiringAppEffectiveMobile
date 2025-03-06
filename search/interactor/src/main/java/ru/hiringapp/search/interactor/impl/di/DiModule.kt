package ru.hiringapp.search.interactor.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.source.offer.OfferLocalDataSource
import ru.hiringapp.search.interactor.api.source.offer.OfferRemoteDataSource
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyLocalDataSource
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyRemoteDataSource
import ru.hiringapp.search.interactor.api.usecase.ChangeVacancyIsFavouriteUseCase
import ru.hiringapp.search.interactor.api.usecase.GetOffersUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveFavouriteVacanciesUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveOffersUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveVacanciesUseCase
import ru.hiringapp.search.interactor.impl.repository.SearchRepositoryImpl
import ru.hiringapp.search.interactor.impl.source.offer.local.OfferLocalDataSourceImpl
import ru.hiringapp.search.interactor.impl.source.offer.remote.OfferApi
import ru.hiringapp.search.interactor.impl.source.offer.remote.OfferRemoteDataSourceImpl
import ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response.ButtonDto
import ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response.OfferDto
import ru.hiringapp.search.interactor.impl.source.offer.remote.dto.response.OffersResponse
import ru.hiringapp.search.interactor.impl.source.vacancy.local.VacancyLocalDataSourceImpl
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.VacancyApi
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.VacancyRemoteDataSourceImpl
import ru.hiringapp.search.interactor.impl.usecase.ChangeVacancyIsFavouriteUseCaseImpl
import ru.hiringapp.search.interactor.impl.usecase.GetOffersUseCaseImpl
import ru.hiringapp.search.interactor.impl.usecase.ObserveFavouriteVacanciesUseCaseImpl
import ru.hiringapp.search.interactor.impl.usecase.ObserveOffersUseCaseIMpl
import ru.hiringapp.search.interactor.impl.usecase.ObserveVacanciesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindChangeVacancyIsFavouriteUseCase(
        useCaseImpl: ChangeVacancyIsFavouriteUseCaseImpl,
    ): ChangeVacancyIsFavouriteUseCase

    @Binds
    fun bindObserveFavouriteVacanciesUseCase(
        useCaseImpl: ObserveFavouriteVacanciesUseCaseImpl,
    ): ObserveFavouriteVacanciesUseCase

    @Binds
    fun bindObserveOffersUseCase(
        useCaseImpl: ObserveOffersUseCaseIMpl,
    ): ObserveOffersUseCase

    @Binds
    fun bindObserveVacanciesUseCase(
        useCaseImpl: ObserveVacanciesUseCaseImpl,
    ): ObserveVacanciesUseCase

    @Binds
    fun bindGetOffersUseCase(
        useCaseImpl: GetOffersUseCaseImpl,
    ): GetOffersUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindSearchRepository(
        repositoryImpl: SearchRepositoryImpl,
    ): SearchRepository
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

    @Binds
    fun bindVacancyLocalDataSource(
        localSourceImpl: VacancyLocalDataSourceImpl,
    ): VacancyLocalDataSource

    @Binds
    fun bindVacancyRemoteDataSource(
        remoteSourceImpl: VacancyRemoteDataSourceImpl,
    ): VacancyRemoteDataSource
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
            ),
            OfferDto(
                id = "level_up_resume",
                title = "Поднять резюме в поиске",
                description = "", // Описание отсутствует в JSON
                button = ButtonDto(text = "Поднять"), // Кнопка присутствует
                link = "https://hh.ru/mentors?from=footer_new&hhtmFromLabel=footer_new&hhtmFrom=main&purposeId=1"
            ),
            OfferDto(
                id = "temporary_job",
                title = "Временная работа или подработка",
                description = "", // Описание отсутствует в JSON
                button = null, // Кнопка отсутствует
                link = "https://hh.ru/"
            ),
            OfferDto(
                id = null, // ID отсутствует в JSON
                title = "Полезные статьи и советы",
                description = "", // Описание отсутствует в JSON
                button = null, // Кнопка отсутствует
                link = "https://hh.ru/articles?hhtmFrom=main"
            )
        )
        return object: OfferApi {
            override suspend fun getOffers(): Response<OffersResponse> {
                return Response.success(OffersResponse(
                    offers = offers
                ))
            }

        }

    }

    @Provides
    @Singleton
    fun provideVacancyApi(retrofit: Retrofit): VacancyApi {
        return retrofit.create(VacancyApi::class.java)
    }
}