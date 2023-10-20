package com.tommygr.gamequiz.domain.repositories



import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.Flow

interface StatisticRepository {
    fun observeStatistic(userId: String, forceUpdate: Boolean = false): Resource<Flow<StatisticDomainModel>>

    suspend fun getStatistic(userId: String, forceUpdate: Boolean = false): Resource<StatisticDomainModel>

    suspend fun refreshStatistic(userId: String): Resource<StatisticDomainModel>

    suspend fun addNewStatistic(statisticDomainModel: StatisticDomainModel): Resource<Unit>

    suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel): Resource<Unit>

    suspend fun deleteStatistic(userId: String): Resource<Unit>

}