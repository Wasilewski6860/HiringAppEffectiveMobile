package ru.hiringapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.hiringapp.database.dao.OfferDao
import ru.hiringapp.database.dao.VacancyDao
import ru.hiringapp.database.entity.OfferEntity
import ru.hiringapp.database.entity.VacancyEntity

@Database(
    entities = [
        OfferEntity::class,
        VacancyEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ButtonConverter::class,
    AddressConverter::class,
    ExperienceConverter::class,
    SalaryConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao
    abstract fun vacancyDao(): VacancyDao
}