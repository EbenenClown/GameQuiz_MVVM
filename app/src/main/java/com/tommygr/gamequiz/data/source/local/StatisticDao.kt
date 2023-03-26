package com.tommygr.gamequiz.data.source.local


import androidx.room.*
import com.tommygr.gamequiz.data.Statistic
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticDao {
    @Query("SELECT * FROM statistic WHERE userId=:userId")
    fun observeStatistic(userID: String): Flow<Statistic>

    @Query("SELECT * FROM statistic WHERE userId=:userId")
    fun getStatistic(userId: String): Statistic

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertStatistic(statistic: Statistic)

    @Update
    fun updateStatistic(statistic: Statistic)

    @Delete
    fun deleteStatistic(statistic: Statistic)

    @Query("DELETE FROM statistic")
    fun clear()
}