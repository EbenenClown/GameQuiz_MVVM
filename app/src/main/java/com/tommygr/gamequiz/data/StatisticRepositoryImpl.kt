package com.tommygr.gamequiz.data

import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.local.localdatasources.LocalStatisticDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import com.tommygr.gamequiz.domain.StatisticRepository
import kotlinx.coroutines.flow.Flow

class StatisticRepositoryImpl(private val localStatisticDataSource: LocalStatisticDataSource
                                , private val remoteStatisticDataSource: RemoteStatisticDataSource):
    StatisticRepository {

    override fun observeStatistic(userId: String): Flow<StatisticDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getStatisticById(userId: String): StatisticDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun refreshStatistic() {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewStatistic(statisticDataModel: StatisticDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStatistic(statisticDataModel: StatisticDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteStatistic(statisticDataModel: StatisticDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}