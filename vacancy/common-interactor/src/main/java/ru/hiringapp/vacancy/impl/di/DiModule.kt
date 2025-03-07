package ru.hiringapp.vacancy.impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import ru.hiringapp.domain.vacancy.Salary
import ru.hiringapp.vacancy.api.repository.VacancyRepository
import ru.hiringapp.vacancy.api.source.VacancyLocalDataSource
import ru.hiringapp.vacancy.api.source.VacancyRemoteDataSource
import ru.hiringapp.vacancy.api.usecase.ChangeVacancyIsFavouriteUseCase
import ru.hiringapp.vacancy.api.usecase.ObserveFavouriteVacanciesCountUseCase
import ru.hiringapp.vacancy.api.usecase.ObserveFavouriteVacanciesUseCase
import ru.hiringapp.vacancy.api.usecase.ObserveVacanciesUseCase
import ru.hiringapp.vacancy.api.usecase.UpdateVacanciesUseCase
import ru.hiringapp.vacancy.impl.repository.VacancyRepositoryImpl
import ru.hiringapp.vacancy.impl.source.local.VacancyLocalDataSourceImpl
import ru.hiringapp.vacancy.impl.source.remote.VacancyApi
import ru.hiringapp.vacancy.impl.source.remote.VacancyRemoteDataSourceImpl
import ru.hiringapp.vacancy.impl.source.remote.dto.response.AddressDto
import ru.hiringapp.vacancy.impl.source.remote.dto.response.ExperienceDto
import ru.hiringapp.vacancy.impl.source.remote.dto.response.VacanciesResponse
import ru.hiringapp.vacancy.impl.source.remote.dto.response.VacancyDto
import ru.hiringapp.vacancy.impl.usecase.ChangeVacancyIsFavouriteUseCaseImpl
import ru.hiringapp.vacancy.impl.usecase.ObserveFavouriteVacanciesCountUseCaseImpl
import ru.hiringapp.vacancy.impl.usecase.ObserveFavouriteVacanciesUseCaseImpl
import ru.hiringapp.vacancy.impl.usecase.ObserveVacanciesUseCaseImpl
import ru.hiringapp.vacancy.impl.usecase.UpdateVacanciesUseCaseImpl
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
    fun bindObserveFavouriteVacanciesCountUseCase(
        useCaseImpl: ObserveFavouriteVacanciesCountUseCaseImpl,
    ): ObserveFavouriteVacanciesCountUseCase

    @Binds
    fun bindObserveVacanciesUseCase(
        useCaseImpl: ObserveVacanciesUseCaseImpl,
    ): ObserveVacanciesUseCase

    @Binds
    fun bindUpdateVacanciesUseCase(
        useCaseImpl: UpdateVacanciesUseCaseImpl,
    ): UpdateVacanciesUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindVacancyRepository(
        repositoryImpl: VacancyRepositoryImpl,
    ): VacancyRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

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
//    fun provideVacancyApi(retrofit: Retrofit): VacancyApi {
//        return retrofit.create(VacancyApi::class.java)
//    }

    @Provides
    @Singleton
    fun provideVacancyApi(retrofit: Retrofit): VacancyApi {
        val mockVacancies = listOf(
            VacancyDto(
                id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
                title = "UI/UX дизайнер",
                company = "Мобирикс",
                address = AddressDto(
                    town = "Минск",
                    street = "улица Бирюзова",
                    house = "4/5"
                ),
                experience = ExperienceDto(
                    previewText = "Опыт от 1 до 3 лет",
                    text = "1–3 года"
                ),
                publishedDate = "2024-02-20",
                isFavorite = false,
                salary = Salary(full = "Уровень дохода не указан"),
                schedules = listOf("полная занятость", "полный день"),
                description = "Мы ищем специалиста на позицию UX/UI Designer...",
                responsibilities = "- проектирование пользовательских сценариев и создание прототипов...",
                questions = listOf(
                    "Где располагается место работы?",
                    "Какой график работы?",
                    "Вакансия открыта?",
                    "Какая оплата труда?"
                ),
                lookingNumber = 2,
                appliedNumber = 147
            ),
            VacancyDto(
                id = "54a876a5-2205-48ba-9498-cfecff4baa6e",
                title = "UI/UX-дизайнер / Web-дизайнер / Дизайнер интерфейсов",
                company = "Шафигуллин Шакир",
                address = AddressDto(
                    town = "Казань",
                    street = "улица Чистопольская",
                    house = "20/10"
                ),
                experience = ExperienceDto(
                    previewText = "Опыт от 1 до 3 лет",
                    text = "1–3 года"
                ),
                publishedDate = "2024-03-06",
                isFavorite = false,
                salary = Salary(
                    short = "20 000 до 50 000 ₽",
                    full = "от 20 000 до 50 000 ₽ на руки"
                ),
                schedules = listOf("частичная занятость", "полный день"),
                description = "Мы разрабатываем мобильные приложения...",
                responsibilities = "- Разработка дизайна Web+App (обязательно Figma)...",
                questions = listOf(
                    "Где располагается место работы?",
                    "Какой график работы?",
                    "Как с вами связаться?"
                ),
                lookingNumber = 17
            ),
            VacancyDto(
                id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
                title = "UX/UI Designer",
                company = "Aston",
                address = AddressDto(
                    town = "Казань",
                    street = "улица Пушкина",
                    house = "2"
                ),
                experience = ExperienceDto(
                    previewText = "Опыт от 3 до 6 лет",
                    text = "3–6 лет"
                ),
                publishedDate = "2024-02-28",
                isFavorite = true,
                salary = Salary(full = "Уровень дохода не указан"),
                schedules = listOf("полная занятость", "удаленная работа"),
                description = "Мы – аутсорсинговая аккредитованная IT-компания Aston...",
                responsibilities = "- совместно с Product Owner определять бизнес-метрики...",
                questions = listOf(
                    "Где располагается место работы?",
                    "Какой график работы?",
                    "Какая оплата труда?"
                ),
                appliedNumber = 11
            ),
            VacancyDto(
                id = "74c84407-52e1-4cce-a73a-ff2d3ac031b1",
                title = "Android Разработчик",
                company = "Surf",
                address = AddressDto(
                    town = "Воронеж",
                    street = "Проспект Революции",
                    house = "14"
                ),
                experience = ExperienceDto(
                    previewText = "Опыт от 1 до 3 лет",
                    text = "1–3 года"
                ),
                publishedDate = "2024-01-23",
                isFavorite = true,
                salary = Salary(full = "20 000 до 50 000 ₽"),
                schedules = listOf("полная занятость", "удаленная работа"),
                description = "Мы – аутсорсинговая аккредитованная IT-компания Surf...",
                responsibilities = "- Разрабатывать мобильные приложения...",
                questions = listOf(
                    "Где располагается место работы?",
                    "Какой график работы?",
                    "Какая оплата труда?"
                ),
                appliedNumber = 19
            )
        )
        return object : VacancyApi {
            override suspend fun getVacancies(): Response<VacanciesResponse> {
                return Response.success(
                    VacanciesResponse(
                        vacancies = mockVacancies
                    )
                )
            }

        }
    }
}