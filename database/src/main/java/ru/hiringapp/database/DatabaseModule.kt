package ru.hiringapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.hiringapp.database.dao.OfferDao
import ru.hiringapp.database.dao.VacancyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "hiring_database.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideOfferDao(database: AppDatabase): OfferDao =
        database.offerDao()

    @Provides
    @Singleton
    fun provideLocationDao(database: AppDatabase): VacancyDao =
        database.vacancyDao()
}