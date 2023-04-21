package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel

import kotlinx.coroutines.flow.Flow

interface StatisticDataSource {
    fun observeStatistic(userId: String): Flow<StatisticDataModel>

    suspend fun getStatistic(userId: String): StatisticDataModel

    suspend fun saveStatistic(statisticDataModel: StatisticDataModel)

    suspend fun updateStatistic(statisticDataModel: StatisticDataModel)

    suspend fun deleteStatisticWithId(userId: String)

    suspend fun clear()
}