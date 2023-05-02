package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.database.StatisticDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StatisticRepositoryImpl(private val localDataSource: StatisticDao
                                , private val remoteDataSource: RemoteStatisticDataSource): StatisticRepository {
    override fun observeStatistic(forceUpdate: Boolean): Flow<StatisticDomainModel> = localDataSource.observeStatistic().map { it.toDomainModel() }

    override suspend fun getStatistic(forceUpdate: Boolean): StatisticDomainModel = localDataSource.getStatistic().toDomainModel()

    override suspend fun refreshStatistic(userId: String) {
        val remoteStatistic = remoteDataSource.getStatistic(userId)
        localDataSource.insertNewStatistic(remoteStatistic)
    }

    override suspend fun addNewStatistic(statisticDomainModel: StatisticDomainModel) {
        localDataSource.insertNewStatistic(statisticDomainModel.toDataModel())
        remoteDataSource.addNewStatistic(statisticDomainModel.toDataModel())
    }

    override suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel) {
        localDataSource.updateStatistic(statisticDomainModel.toDataModel())
    }

    override suspend fun deleteStatistic(userId: String) {
        localDataSource.deleteStatistic(userId)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }


}