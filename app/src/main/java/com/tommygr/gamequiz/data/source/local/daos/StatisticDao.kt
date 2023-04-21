package com.tommygr.gamequiz.data.source.local.daos


import androidx.room.*
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticDao {
    @Query("SELECT * FROM statistic WHERE userId=:userId")
    fun observeStatistic(userId: String): Flow<StatisticDataModel>

    @Query("SELECT * FROM statistic WHERE userId=:userId")
    fun getStatistic(userId: String): StatisticDataModel

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertStatistic(statisticDataModel: StatisticDataModel)

    @Update
    fun updateStatistic(statisticDataModel: StatisticDataModel)

    @Query("DELETE FROM statistic WHERE userId=:userId")
    fun deleteStatistic(userId: String)

    @Query("DELETE FROM statistic")
    fun clear()
}