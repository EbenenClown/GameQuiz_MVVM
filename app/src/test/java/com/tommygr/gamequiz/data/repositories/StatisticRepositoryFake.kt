package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class StatisticRepositoryFake: StatisticRepository {
    private val statisticFlow = MutableStateFlow(mutableListOf<StatisticDomainModel>())
    private val statisticList = mutableListOf<StatisticDomainModel>()
    var isError = false

    override fun observeStatistic(forceUpdate: Boolean): Resource<Flow<StatisticDomainModel>> {
        return if(!isError) {
            statisticFlow.value.firstOrNull()?.let {
                Resource.Success(it)
            }
            Resource.Error("test statistic flow is empty")
        } else {
            Resource.Error("test observation failed")
        }
    }

    override suspend fun getStatistic(forceUpdate: Boolean): Resource<StatisticDomainModel> {
        return if(!isError) {
            statisticList.firstOrNull()?.let {
                Resource.Success(it)
            }
            Resource.Error("test statisticList is empty")
        } else {
            Resource.Error("test get Statistic failed")
        }
    }

    override suspend fun refreshStatistic(userId: String): Resource<StatisticDomainModel> {
        return if(!isError) {
            statisticList.firstOrNull()?.let {
                Resource.Success(it)
            }
            Resource.Error("test statisticList is empty")
        } else {
            Resource.Error("test refresh Statistic failed")
        }
    }

    override suspend fun addNewStatistic(statisticDomainModel: StatisticDomainModel): Resource<Unit> {
        return if(!isError) {
            statisticList.add(statisticDomainModel)
            statisticFlow.map {
                it.add(statisticDomainModel)
            }
            Resource.Success(Unit)
        } else {
            Resource.Error("test refresh Statistic failed")
        }

    }

    override suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel): Resource<Unit> {
        return if(!isError) {
            statisticList.firstOrNull()?.let {
                statisticList[0] = statisticDomainModel
                statisticFlow.value[0] = statisticDomainModel
                Resource.Success(Unit)
            }
            Resource.Error("test update failed empty list")
        } else {
            Resource.Error("test update Statistic failed")
        }
    }

    override suspend fun deleteStatistic(userId: String): Resource<Unit> {
        return if(!isError) {
            statisticList.firstOrNull()?.let {
                statisticList.remove(statisticList[0])
                statisticFlow.value.remove(statisticList[0])
                Resource.Success(Unit)
            }
            Resource.Error("test delete failed empty list")
        } else {
            Resource.Error("test delete Statistic failed")
        }
    }
}