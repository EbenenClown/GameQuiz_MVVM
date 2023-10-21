package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatisticRepositoryImpl @Inject constructor(private val localDataSource: StatisticDao
                                                 , private val remoteDataSource: RemoteStatisticDataSource): StatisticRepository {
    override fun observeStatistic(userId: String, forceUpdate: Boolean): Resource<Flow<StatisticDomainModel>> {
        return try {
            Resource.Success(localDataSource.observeStatistic(userId).map { it.toDomainModel() })
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }

    }

    override suspend fun getStatistic(userId: String, forceUpdate: Boolean): Resource<StatisticDomainModel> {
        return try {
            Resource.Success(localDataSource.getStatistic(userId).toDomainModel())
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }

    }

    override suspend fun refreshStatistic(userId: String): Resource<StatisticDomainModel> {
        return try {
            val elementBody = remoteDataSource.getStatistic(userId).body()
            elementBody?.values?.let { statisticCollection ->
                val statistic = statisticCollection.toList()[0]
                localDataSource.insertNewStatistic(statistic)
                Resource.Success(statistic)
            }
            Resource.Error("statistic is null")

        } catch (e: Exception) {
            Resource.Error(e.toString())
        }

    }

    override suspend fun addNewStatistic(statisticDomainModel: StatisticDomainModel): Resource<Unit> {
        return try {
            localDataSource.insertNewStatistic(statisticDomainModel.toDataModel())
            remoteDataSource.addNewStatistic(statisticDomainModel.toDataModel())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }

    }

    override suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel): Resource<Unit> {
        return try {
            localDataSource.updateStatistic(statisticDomainModel.toDataModel())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }

    override suspend fun deleteStatistic(userId: String): Resource<Unit> {
        return try {
            localDataSource.deleteStatistic(userId)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }
}