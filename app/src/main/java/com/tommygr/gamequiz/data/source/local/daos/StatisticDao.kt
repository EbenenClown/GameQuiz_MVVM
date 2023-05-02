package com.tommygr.gamequiz.data.source.local.daos


import androidx.room.*
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StatisticDao {
    @Query("SELECT * FROM statistic")
    fun observeStatistic(): Flow<StatisticDataModel>

    @Query("SELECT * FROM statistic")
    fun getStatistic(): StatisticDataModel

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertNewStatistic(statisticDataModel: StatisticDataModel)

    @Update
    fun updateStatistic(statisticDataModel: StatisticDataModel)

    @Query("DELETE FROM statistic WHERE userId=:userId")
    fun deleteStatistic(userId: String)

    @Query("DELETE FROM statistic")
    fun clear()
}