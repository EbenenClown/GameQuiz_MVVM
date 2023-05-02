package com.tommygr.gamequiz.domain.repositories



import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import kotlinx.coroutines.flow.Flow

interface StatisticRepository {
    fun observeStatistic(forceUpdate: Boolean = false): Flow<StatisticDomainModel>

    suspend fun getStatistic(forceUpdate: Boolean = false): StatisticDomainModel

    suspend fun refreshStatistic(userId: String)

    suspend fun addNewStatistic(statisticDomainModel: StatisticDomainModel)

    suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel)

    suspend fun deleteStatistic(userId: String)

    suspend fun clear()
}