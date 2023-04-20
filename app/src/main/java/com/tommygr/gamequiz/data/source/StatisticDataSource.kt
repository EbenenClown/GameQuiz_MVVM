package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.Statistic

import kotlinx.coroutines.flow.Flow

interface StatisticDataSource {
    fun observeStatistic(userId: String): Flow<Statistic>

    suspend fun getStatistic(userId: String): Statistic

    suspend fun refreshStatistic()

    suspend fun saveStatistic(statistic: Statistic)

    suspend fun updateStatistic(statistic: Statistic)

    suspend fun deleteStatisticWithId(userId: String)

    suspend fun clear()
}