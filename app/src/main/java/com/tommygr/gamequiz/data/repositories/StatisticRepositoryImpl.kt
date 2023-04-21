package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.localdatasources.LocalStatisticDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import kotlinx.coroutines.flow.map

class StatisticRepositoryImpl(private val localStatisticDataSource: LocalStatisticDataSource
                                , private val remoteStatisticDataSource: RemoteStatisticDataSource):
    StatisticRepository {
    override fun observeStatistic(userId: String) = localStatisticDataSource.observeStatistic(userId).map { it.toDomainModel() }

    override suspend fun getStatisticById(userId: String) = localStatisticDataSource.getStatistic(userId).toDomainModel()

    override suspend fun refreshStatistic(userId: String) {
        val remoteStatistic = remoteStatisticDataSource.getStatistic(userId)
        localStatisticDataSource.saveStatistic(remoteStatistic)
    }

    override suspend fun saveNewStatistic(statisticDomainModel: StatisticDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStatistic(statisticDomainModel: StatisticDomainModel) {
        localStatisticDataSource.updateStatistic(statisticDomainModel.toDataModel())
    }

    override suspend fun deleteStatistic(statisticDomainModel: StatisticDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }


}