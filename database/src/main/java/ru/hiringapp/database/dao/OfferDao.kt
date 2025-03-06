package ru.hiringapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.hiringapp.database.entity.OfferEntity

@Dao
interface OfferDao {
    @Query("DELETE FROM offer")
    fun deleteTable()

    @Insert(entity = OfferEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(offers: List<OfferEntity>)

    @Query("SELECT * FROM offer")
    fun getOffers(): List<OfferEntity>

    @Query("SELECT * FROM offer")
    fun getOffersFlow(): Flow<List<OfferEntity>>
}