package com.tommygr.gamequiz.domain.repositories



import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import kotlinx.coroutines.flow.Flow

interface StatisticRepository {
    fun observeStatistic(userId: String): Flow<StatisticDomainModel>

    suspend fun getStatisticById(userId: String): StatisticDomainModel

    suspend fun refreshStatistic(userId: String)

    suspend fun saveNewStatistic(statisticDomainModel: StatisticDomainModel)

    suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel)

    suspend fun deleteStatistic(statisticDomainModel: StatisticDomainModel)

    suspend fun clear()
}