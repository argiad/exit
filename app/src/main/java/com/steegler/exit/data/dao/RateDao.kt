package com.steegler.exit.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.steegler.exit.data.entity.RateEntity


@Dao
interface RateDao {


    @Query("SELECT * FROM RateEntity ORDER BY symbol ASC ")
    fun getAll(): RateEntity?

    @Query("SELECT * FROM RateEntity WHERE id == (:rateID) LIMIT 1")
    fun getRateBy(rateID: String): RateEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg messages: RateEntity)

    @Delete
    fun delete(message: RateEntity)

    @Query("DELETE FROM RateEntity")
    fun cleanTable()
}