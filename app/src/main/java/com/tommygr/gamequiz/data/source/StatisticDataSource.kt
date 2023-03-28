package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.Statistic

import com.tommygr.gamequiz.data.Result
import kotlinx.coroutines.flow.Flow

interface StatisticDataSource {
    fun observeStatistic(userId: String): Flow<Result<Statistic>>

    suspend fun getStatistic(userId: String): Result<Statistic>

    suspend fun refreshStatistic()

    suspend fun saveStatistic(statistic: Statistic)

    suspend fun updateStatistic(statistic: Statistic)

    suspend fun deleteStatisticWithId(userId: String)

    suspend fun clear()
}