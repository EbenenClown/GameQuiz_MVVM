package com.tommygr.gamequiz.domain


import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import kotlinx.coroutines.flow.Flow

interface StatisticRepository {
    fun observeStatistic(userId: String): Flow<StatisticDataModel>

    suspend fun getStatisticById(userId: String): StatisticDataModel

    suspend fun refreshStatistic()

    suspend fun saveNewStatistic(statisticDataModel: StatisticDataModel)

    suspend fun updateStatistic(statisticDataModel: StatisticDataModel)

    suspend fun deleteStatistic(statisticDataModel: StatisticDataModel)

    suspend fun clear()
}