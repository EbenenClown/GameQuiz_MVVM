package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.StatisticDataSource
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteStatisticDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): StatisticDataSource {
    override fun observeStatistic(userId: String): Flow<StatisticDataModel> = flow { emit(firebaseAPI.getStatisticById(userId)) }

    override suspend fun getStatistic(userId: String): StatisticDataModel = withContext(dispatcher) {
        return@withContext firebaseAPI.getStatisticById(userId)
    }

    override suspend fun saveStatistic(statisticDataModel: StatisticDataModel) = withContext(dispatcher) {
        return@withContext firebaseAPI.saveStatistic(statisticDataModel)
    }

    override suspend fun updateStatistic(statisticDataModel: StatisticDataModel) = withContext(dispatcher) {
        return@withContext firebaseAPI.updateStatistic(statisticDataModel.userId, statisticDataModel)
    }

    override suspend fun deleteStatisticWithId(userId: String) = withContext(dispatcher) {
        return@withContext firebaseAPI.deleteStatistic(userId)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}