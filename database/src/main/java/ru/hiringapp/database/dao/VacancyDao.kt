package ru.hiringapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.hiringapp.database.entity.VacancyEntity

@Dao
interface VacancyDao {
    @Query("DELETE FROM vacancy")
    fun deleteTable()

    @Insert(entity = VacancyEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancies(vacancies: List<VacancyEntity>)

    @Query("UPDATE vacancy SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)

    @Query("SELECT * FROM vacancy")
    fun getVacanciesFlow() : Flow<List<VacancyEntity>>

    @Query("SELECT * FROM vacancy WHERE is_favorite")
    fun getFavouriteVacanciesFlow() : Flow<List<VacancyEntity>>
}