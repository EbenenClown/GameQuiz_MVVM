package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.Statistic

import com.tommygr.gamequiz.data.Result
import kotlinx.coroutines.flow.Flow

interface StatisticDataSource {
    fun observeStatistic(userId: String): Flow<Result<Statistic>>

    suspend fun getStatistic(userId: String): Result<Statistic>

    suspend fun refreshStatistic()

    suspend fun saveStatistic(userId: String)

    suspend fun updateStatistic(userId: String)

    suspend fun deleteStatistic(userId: String)

    suspend fun clear()
}