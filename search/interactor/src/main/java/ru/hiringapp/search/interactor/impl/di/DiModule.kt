package ru.hiringapp.search.interactor.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.hiringapp.search.interactor.api.repository.SearchRepository
import ru.hiringapp.search.interactor.api.source.offer.OfferLocalDataSource
import ru.hiringapp.search.interactor.api.source.offer.OfferRemoteDataSource
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyLocalDataSource
import ru.hiringapp.search.interactor.api.source.vacancy.VacancyRemoteDataSource
import ru.hiringapp.search.interactor.api.usecase.ChangeVacancyIsFavouriteUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveFavouriteVacanciesUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveOffersUseCase
import ru.hiringapp.search.interactor.api.usecase.ObserveVacanciesUseCase
import ru.hiringapp.search.interactor.impl.repository.SearchRepositoryImpl
import ru.hiringapp.search.interactor.impl.source.offer.local.OfferLocalDataSourceImpl
import ru.hiringapp.search.interactor.impl.source.offer.remote.OfferApi
import ru.hiringapp.search.interactor.impl.source.offer.remote.OfferRemoteDataSourceImpl
import ru.hiringapp.search.interactor.impl.source.vacancy.local.VacancyLocalDataSourceImpl
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.VacancyApi
import ru.hiringapp.search.interactor.impl.source.vacancy.remote.VacancyRemoteDataSourceImpl
import ru.hiringapp.search.interactor.impl.usecase.ChangeVacancyIsFavouriteUseCaseImpl
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

    @Provides
    @Singleton
    fun provideOfferApi(retrofit: Retrofit): OfferApi {
        return retrofit.create(OfferApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVacancyApi(retrofit: Retrofit): VacancyApi {
        return retrofit.create(VacancyApi::class.java)
    }
}