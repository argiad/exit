package com.steegler.exit.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.steegler.exit.data.entity.RateEntity

@Dao
interface RateDao {

    @Query("SELECT * FROM RateEntity ORDER BY symbol DESC ")
    fun getAll(): LiveData<List<RateEntity>>

    @Query("SELECT * FROM RateEntity WHERE id == (:rateID) LIMIT 1")
    fun getRateBy(rateID: String): RateEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg messages: RateEntity)

    @Delete
    suspend fun delete(message: RateEntity)

    @Query("DELETE FROM RateEntity")
    suspend fun cleanTable()
}